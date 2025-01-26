package com.booking.qs_availability_service.services;

import com.booking.qs_availability_service.domain.timeslots.AppointmentSlots;
import com.booking.qs_availability_service.dtos.Message;
import com.booking.qs_availability_service.dtos.Response;
import com.booking.qs_availability_service.dtos.timeslots.CreateApptSlots;
import com.booking.qs_availability_service.repositories.AppointmentSlotsRepository;
import com.booking.qs_availability_service.services.interfaces.TimeSlotsService;
import com.booking.qs_availability_service.utils.TimeSlotsUtils;
import org.springframework.stereotype.Service;

@Service
public class TimeSlotsServiceImpl implements TimeSlotsService {

    private AppointmentSlotsRepository apptRepo;

    public Response<AppointmentSlots> createApptSlots(CreateApptSlots request) {
        var response = new Response<AppointmentSlots>();
        try {
            var apptSlot = TimeSlotsUtils.createAppointmentSlots(request);
            apptSlot.setNew(true);
            apptSlot =  apptRepo.saveAndFlush(apptSlot);
            response
                    .withSuccess(true)
                    .with(apptSlot);
        } catch (Throwable e) {
            response
                    .withSuccess(false)
                    .addMessage(new Message("500", e.getMessage()));
        }
        return response;
    }
}
