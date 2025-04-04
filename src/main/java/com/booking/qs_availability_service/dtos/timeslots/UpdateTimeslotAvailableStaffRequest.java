package com.booking.qs_availability_service.dtos.timeslots;

import lombok.Data;

@Data
public class UpdateTimeslotAvailableStaffRequest extends UpdateTimeslotRequest {
    private Integer availableStaff;
    private String date;
}
