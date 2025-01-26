package com.booking.qs_availability_service.utils;

import com.booking.qs_availability_service.domain.timeslots.AppointmentSlots;
import com.booking.qs_availability_service.dtos.timeslots.CreateApptSlots;

import java.util.UUID;

public class TimeSlotsUtils {

    public static AppointmentSlots createAppointmentSlots(CreateApptSlots cas) {
        var apptSlots = new AppointmentSlots();
        apptSlots.setAppointmentType(cas.getAppointmentType());
        apptSlots.setSlotDurationMinutes(cas.getSlotDurationMinutes());
        apptSlots.setLocationId(cas.getLocationId());
        apptSlots.setLocationId(cas.getLocationId());
        apptSlots.setId(generateId());
        return apptSlots;
    }

    private static String generateId() {
        var uuid = UUID.randomUUID();
        return uuid.toString().substring(0,7);
    }
}
