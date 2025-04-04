package com.booking.qs_availability_service.services.interfaces;

import com.booking.qs_availability_service.domain.timeslots.AppointmentSlots;
import com.booking.qs_availability_service.dtos.Response;
import com.booking.qs_availability_service.dtos.timeslots.*;

public interface TimeSlotsService {
    public Response<AppointmentSlots> createApptSlots(CreateApptSlots request);
    public Response<ListTimeslotsResponse> listAvailableTimeslots(ListTimeslotsRequest request);
    public Response<VisitTimeslotReserved> reserveVisitTimeslot(CreateVisitRequest request);
}
