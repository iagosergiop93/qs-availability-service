package com.booking.qs_availability_service.services.interfaces;

import com.booking.qs_availability_service.domain.timeslots.AppointmentSlots;
import com.booking.qs_availability_service.dtos.Response;
import com.booking.qs_availability_service.dtos.timeslots.CreateApptSlots;

public interface TimeSlotsService {
    public Response<AppointmentSlots> createApptSlots(CreateApptSlots request);
}
