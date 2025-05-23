package com.booking.qs_availability_service.dtos.timeslots;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CreateApptSlots {
    private String orgId;
    private String locationId;
    private Integer slotDurationMinutes;
    private LocalDate startDate;
    private LocalDate endDate;
}
