package com.booking.qs_availability_service.utils;

import com.booking.qs_availability_service.domain.timeslots.Timeslot;
import com.booking.qs_availability_service.dtos.timeslots.CreateApptSlots;

import java.time.LocalDate;
import java.time.LocalTime;

public class TimeslotsUtils {

    public static Timeslot createTimeslots(String orgId, String locationId) {
        var timeslot = new Timeslot();
        timeslot.setOrgId(orgId);
        timeslot.setLocationId(locationId);
        timeslot.setSlotDurationMin(0);
        timeslot.setBookings(0);
        timeslot.setTotalAvailableStaff(0);
        return timeslot;
    }

    public static String generateId(CreateApptSlots cas, LocalDate date, LocalTime startTime) {
        var sb = new StringBuilder();
        var DELIMETER = "_";
        sb
                .append(cas.getOrgId())
                .append(DELIMETER)
                .append(cas.getLocationId())
                .append(DELIMETER)
                .append(date.toString())
                .append(DELIMETER)
                .append(startTime.toString());
        return sb.toString();
    }
}
