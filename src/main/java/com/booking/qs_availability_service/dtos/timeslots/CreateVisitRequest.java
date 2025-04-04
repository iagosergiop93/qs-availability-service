package com.booking.qs_availability_service.dtos.timeslots;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class CreateVisitRequest {
    private String id;
    private String orgId;
    private String locationId;
    private LocalDate day;
    private LocalTime startTime;
    private LocalTime endTime;
    private String visitTypeCode;
}
