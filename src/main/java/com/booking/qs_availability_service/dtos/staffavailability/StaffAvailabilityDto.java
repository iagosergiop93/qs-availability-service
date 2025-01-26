package com.booking.qs_availability_service.dtos.staffavailability;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
public class StaffAvailabilityDto {
    private String id;
    private String orgId;
    private String locationId;
    private String startTime;
    private String endTime;
    private Instant date;
    private String availabilityType;
    private Integer availableStaffCount;

    public StaffAvailabilityDto(String id, String orgId, String locationId, String startTime, String endTime, Instant date, String availabilityType, Integer availableStaffCount) {
        this.id = id;
        this.orgId = orgId;
        this.locationId = locationId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.date = date;
        this.availabilityType = availabilityType;
        this.availableStaffCount = availableStaffCount;
    }
}
