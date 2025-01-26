package com.booking.qs_availability_service.dtos.timeslots;

import lombok.Data;

@Data
public class CreateApptSlots {
    private String orgId;
    private String locationId;
    private String appointmentType;
    private Integer slotDurationMinutes;
}
