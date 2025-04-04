package com.booking.qs_availability_service.dtos.timeslots;

import lombok.Data;

@Data
public class UpdateTimeslotRequest {
    private String transactionId;
    private String orgId;
    private String locationId;
}