package com.booking.qs_availability_service.utils;

import com.booking.qs_availability_service.domain.staffavailability.StaffAvailability;
import com.booking.qs_availability_service.domain.staffavailability.StaffAvailabilityConstants;
import com.booking.qs_availability_service.domain.staffavailability.StaffAvailabilityType;
import com.booking.qs_availability_service.dtos.staffavailability.StaffAvailabilityDto;

public class StaffAvailabilityUtils {

    public static StaffAvailability createStaffAvailability(StaffAvailabilityDto dto) {
        var availability = new StaffAvailability();
        availability.setId(generateId(dto));
        availability.setOrgId(dto.getOrgId());
        availability.setLocationId(dto.getLocationId());
        availability.setAvailableStaffCount(dto.getAvailableStaffCount());
        availability.setAvailabilityType(StaffAvailabilityType.fromValue(dto.getAvailabilityType()));
        availability.setStartTime(dto.getStartTime());
        availability.setEndTime(dto.getEndTime());
        if(dto.getAvailabilityType() != StaffAvailabilityConstants.REGULAR_HOURS) {
            availability.setDate(dto.getDate());
        }
        return availability;
    }

    private static String generateId(StaffAvailabilityDto dto) {
        var sb = new StringBuilder();
        var DELIMETER = "_";
        sb
                .append(dto.getOrgId())
                .append(DELIMETER)
                .append(dto.getLocationId())
                .append(DELIMETER)
                .append(dto.getAvailabilityType());
        if(dto.getAvailabilityType().equalsIgnoreCase(StaffAvailabilityType.TEMP_HOURS.getValue())) {
            sb.append(dto.getDate().toString());
        }

        return sb.toString();
    }
}
