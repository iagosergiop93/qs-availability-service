package com.booking.qs_availability_service.dtos.staffavailability;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
public class StaffAvailabilityDto {
    private String id;
    private String orgId;
    private String locationId;
    private LocalTime startTime;
    private LocalTime endTime;
    private LocalDate date;
    private String availabilityType;
    private Integer availableStaffCount;

        public StaffAvailabilityDto(String id, String orgId, String locationId, Time startTime, Time endTime, LocalDate date, String availabilityType, Integer availableStaffCount) {
        this.id = id;
        this.orgId = orgId;
        this.locationId = locationId;
        this.startTime = startTime.toLocalTime();
        this.endTime = endTime.toLocalTime();
        this.date = date;
        this.availabilityType = availabilityType;
        this.availableStaffCount = availableStaffCount;
    }
}
