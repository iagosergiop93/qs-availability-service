package com.booking.qs_availability_service.services;

import com.booking.qs_availability_service.domain.staffavailability.StaffAvailabilityType;
import com.booking.qs_availability_service.domain.timeslots.AppointmentSlots;
import com.booking.qs_availability_service.domain.timeslots.Timeslot;
import com.booking.qs_availability_service.dtos.Message;
import com.booking.qs_availability_service.dtos.Response;
import com.booking.qs_availability_service.dtos.staffavailability.StaffAvailabilityDto;
import com.booking.qs_availability_service.dtos.timeslots.*;
import com.booking.qs_availability_service.messages.timeslots.producers.TimeslotProducer;
import com.booking.qs_availability_service.repositories.StaffAvailabilityRepository;
import com.booking.qs_availability_service.repositories.TimeslotRepository;
import com.booking.qs_availability_service.services.interfaces.TimeSlotsService;
import com.booking.qs_availability_service.utils.TimeslotsUtils;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TimeSlotsServiceImpl implements TimeSlotsService {

    @Autowired
    private TimeslotRepository timeslotRepo;

    @Autowired
    private TimeslotProducer timeslotProducer;

    @Autowired
    private StaffAvailabilityRepository staffAvailabilityRepo;

    public Response<AppointmentSlots> createApptSlots(CreateApptSlots request) {
        var response = new Response<AppointmentSlots>();
        Optional<StaffAvailabilityDto> staffAvailabilityWeekend = Optional.empty();
        Optional<StaffAvailabilityDto> staffAvailabilityWeek = Optional.empty();
        Optional<StaffAvailabilityDto> staffAvailabilityAllWeek = Optional.empty();
        try {
            var staffAvailabilityList = staffAvailabilityRepo.listAvailability(request.getOrgId(), request.getLocationId());
            staffAvailabilityWeekend = staffAvailabilityList.stream().filter(sa ->
                    sa.getAvailabilityType().equalsIgnoreCase(StaffAvailabilityType.WEEKENDS.getValue())
            ).findFirst();
            staffAvailabilityWeek = staffAvailabilityList.stream().filter(sa ->
                    sa.getAvailabilityType().equalsIgnoreCase(StaffAvailabilityType.WEEKDAYS.getValue()) ||
                        sa.getAvailabilityType().equalsIgnoreCase(StaffAvailabilityType.ALL_WEEK.getValue())
            ).findFirst();
            staffAvailabilityAllWeek = staffAvailabilityList.stream().filter(sa ->
                    sa.getAvailabilityType().equalsIgnoreCase(StaffAvailabilityType.WEEKDAYS.getValue()) ||
                            sa.getAvailabilityType().equalsIgnoreCase(StaffAvailabilityType.ALL_WEEK.getValue())
            ).findFirst();
        } catch (Throwable ex) {
            response
                    .withSuccess(false)
                    .addMessage(new Message("500", ex.getMessage()));
            return response;
        }

        if(staffAvailabilityWeekend.isEmpty() && staffAvailabilityWeek.isEmpty() && staffAvailabilityAllWeek.isEmpty()) {
            response
                    .withSuccess(false)
                    .addMessage(new Message("400", "No Staff Availability found"));
            return response;
        }

        try {
            // Iterate through a date range and update timeslots
            LocalDate startDate = request.getStartDate();
            LocalDate endDate = request.getEndDate();
            for(LocalDate date = startDate; !date.isAfter(endDate); date = date.plusDays(1)) {
                List<Timeslot> timeslots = null;
                if(isWeekend(date) && staffAvailabilityWeekend.isPresent()) {
                    timeslots = createTimeSlotsForSingleDay(date, staffAvailabilityWeekend.get(), request);
                } else if (!isWeekend(date) && staffAvailabilityWeek.isPresent()) {
                    timeslots = createTimeSlotsForSingleDay(date, staffAvailabilityWeek.get(), request);
                } else if(staffAvailabilityAllWeek.isPresent()) {
                    timeslots = createTimeSlotsForSingleDay(date, staffAvailabilityAllWeek.get(), request);
                }
                if(timeslots != null) {
                    timeslotRepo.saveAll(timeslots);
                }
            }
            response.setSuccess(true);
        } catch (Throwable e) {
            response
                    .withSuccess(false)
                    .addMessage(new Message("500", e.getMessage()));
        }
        return response;
    }


    public Response<ListTimeslotsResponse> listAvailableTimeslots(ListTimeslotsRequest request) {
        var response = new Response<ListTimeslotsResponse>();
        try {
            if(request.getStartDate() == null) {
                response.withSuccess(false).addMessage(new Message("400", "Start Date is required"));
                return response;
            }
            if(request.getEndDate() == null) {
                request.setEndDate(request.getStartDate());
            }
            var df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            var startDate = request.getStartDate().format(df);
            var endDate = request.getEndDate().format(df);
            var timeslots = timeslotRepo.queryAvailableSlotsByDateRange(
                    request.getOrgId(), request.getLocationId(),
                    startDate, endDate
            );
            var res = new ListTimeslotsResponse();
            res.setOrgId(request.getOrgId());
            res.setLocationId(request.getLocationId());
            res.setTimeslots(timeslots);
            response
                    .with(res)
                    .setSuccess(true);
        } catch (Throwable ex) {
            response
                .withSuccess(false)
                .addMessage(new Message("500", ex.getMessage()));
        }
        return response;
    }

    @Override
    public Response<VisitTimeslotReserved> reserveVisitTimeslot(CreateVisitRequest request) {

        var response = new Response<VisitTimeslotReserved>();
        var vtr = new VisitTimeslotReserved(request);
        response.with(vtr);

        var df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        var timeFormat = DateTimeFormatter.ofPattern("HH:mm:ss");
        var day = request.getDay().format(df);
        var startTime = request.getStartTime().format(timeFormat);
        var endTime = request.getEndTime().format(timeFormat);
        List<Timeslot> timeslots;

        timeslots = timeslotRepo.queryAvailableSlotsByDateAndTime(
                request.getOrgId(), request.getLocationId(),
                day, startTime, endTime
        );

        var slotAvailable = false;
        Message errorMessage = null;
        if(timeslots == null || timeslots.isEmpty()) {
            vtr.setSlotReserved(false);
            errorMessage = new Message("400", "Slots unavailable");
            response
                    .withSuccess(false)
                    .addMessage(errorMessage);
        }
        else {
            slotAvailable = true;
            for(var timeslot : timeslots) {
                if(timeslot.getBookings() >= timeslot.getTotalAvailableStaff()) {
                    slotAvailable = false;
                    break;
                }
                timeslot.setBookings(timeslot.getBookings() + 1);
            }

            if(slotAvailable) {
                timeslotRepo.saveAllAndFlush(timeslots);
                vtr.setTimeSlotIds(timeslots.stream().map(Timeslot::getId).toList());
                vtr.setSlotReserved(true);
            }
            else {
                errorMessage = new Message("400", "Slots unavailable");
                response.withSuccess(false).addMessage(errorMessage);
            }
        }

        timeslotProducer.visitTimeslotReserved(vtr, errorMessage);

        return response;
    }

    public Response<Boolean> updateTimeslots(UpdateTimeslotRequest request) {
        var response = new Response<Boolean>();
//        var staffAvailabilityList = staffAvailabilityRepo.listAvailability(request.getOrgId(), request.getLocationId());
//        var staffAvailabilityWeekend = staffAvailabilityList.stream().filter(sa ->
//                sa.getAvailabilityType().equals(StaffAvailabilityType.WEEKENDS.getValue())
//        ).toList();
//        var staffAvailabilityWeek = staffAvailabilityList.stream().filter(sa ->
//                sa.getAvailabilityType().equals(StaffAvailabilityType.WEEKDAYS.getValue()) ||
//                    sa.getAvailabilityType().equals(StaffAvailabilityType.ALL_WEEK.getValue())
//        ).toList();
//
//        // Iterate through a date range ie 30 days and update timeslots
//        LocalDate startDate = LocalDate.now();
//        LocalDate endDate = startDate.plusDays(30);
//        for(LocalDate date = startDate; !date.isAfter(endDate); date = date.plusDays(1)) {
//
//        }
//        try (Stream<Timeslot> timeslotStream = timeslotRepo.queryTimeSlotsByDateRange(request.getOrgId(), request.getLocationId(), startDate.toString(), endDate.toString())) {
//            List<Timeslot> timeslots = timeslotStream.map(timeslot -> {
//                var dayOfTheWeek = timeslot.getDay().getDayOfWeek();
//                if(!staffAvailabilityWeekend.isEmpty() && (dayOfTheWeek.equals(DayOfWeek.SUNDAY) || dayOfTheWeek.equals(DayOfWeek.SATURDAY))) {
//                    var sa = staffAvailabilityWeekend.getFirst();
//                    timeslot.setStartTime(sa.getStartTime());
//                    timeslot.setEndTime(sa.getEndTime());
//                }
//                else {
//
//                }
//                return timeslot;
//            }).collect(Collectors.toList());
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
        return response;
    }

    public Response<AvailableSlots> updateTimeslotsAvailableStaff(UpdateTimeslotAvailableStaffRequest request) {
        var response = new Response<AvailableSlots>();
        return response;
    }

    private Boolean isWeekend(LocalDate date) {
        var dayOfTheWeek = date.getDayOfWeek();
        return dayOfTheWeek.equals(DayOfWeek.SUNDAY) || dayOfTheWeek.equals(DayOfWeek.SATURDAY);
    }

    private List<Timeslot> createTimeSlotsForSingleDay(LocalDate date, StaffAvailabilityDto sa, CreateApptSlots cas) {
        var capacity = ((sa.getEndTime().toSecondOfDay() - sa.getStartTime().toSecondOfDay())/60)/cas.getSlotDurationMinutes();
        var timeslots = new ArrayList<Timeslot>(capacity+1);
        for(var time = sa.getStartTime(); time.isBefore(sa.getEndTime()); time = time.plusMinutes(cas.getSlotDurationMinutes())) {
            var ts = TimeslotsUtils.createTimeslots(cas.getOrgId(), cas.getLocationId());
            ts.setDay(date);
            ts.setStartTime(time);
            ts.setEndTime(time.plusMinutes(cas.getSlotDurationMinutes()));
            ts.setSlotDurationMin(
                    (ts.getEndTime().toSecondOfDay() - ts.getStartTime().toSecondOfDay())/60
            );
            ts.setTotalAvailableStaff(sa.getAvailableStaffCount());
            ts.setId(TimeslotsUtils.generateId(cas, date, time));
            timeslots.add(ts);
        }
        return timeslots;
    }
}
