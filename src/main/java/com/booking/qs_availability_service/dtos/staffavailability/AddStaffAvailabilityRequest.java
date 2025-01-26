package com.booking.qs_availability_service.dtos.staffavailability;

import com.booking.qs_availability_service.domain.staffavailability.StaffAvailabilityType;
import lombok.Data;

import java.time.Instant;
import java.time.LocalTime;

@Data
public class AddStaffAvailabilityRequest {
    private String orgId;
    private String locationId;
    private LocalTime startTime;
    private LocalTime endTime;
    private Instant date;
    private StaffAvailabilityType availabilityType;
    private Integer availableStaffCount;
}
