package com.booking.qs_availability_service.utils;

import com.booking.qs_availability_service.domain.staffavailability.StaffAvailability;
import com.booking.qs_availability_service.domain.staffavailability.StaffAvailabilityConstants;
import com.booking.qs_availability_service.domain.staffavailability.StaffAvailabilityType;
import com.booking.qs_availability_service.dtos.staffavailability.StaffAvailabilityDto;

import java.time.Instant;
import java.time.LocalTime;
import java.util.UUID;

public class StaffAvailabilityUtils {

    public static StaffAvailability createStaffAvailability(StaffAvailabilityDto dto) {
        var availability = new StaffAvailability();
        availability.setId(generateId());
        availability.setOrgId(dto.getOrgId());
        availability.setLocationId(dto.getLocationId());
        availability.setAvailableStaffCount(dto.getAvailableStaffCount());
        availability.setAvailabilityType(dto.getAvailabilityType());
        availability.setStartTime(dto.getStartTime());
        availability.setEndTime(dto.getEndTime());
        if(dto.getAvailabilityType() != StaffAvailabilityConstants.REGULAR_HOURS) {
            availability.setDate(dto.getDate());
        }
        return availability;
    }

    private static String generateId() {
        var uuid = UUID.randomUUID();
        return uuid.toString().substring(0,7);
    }
}
