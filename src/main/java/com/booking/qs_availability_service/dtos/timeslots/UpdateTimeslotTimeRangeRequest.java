package com.booking.qs_availability_service.dtos.timeslots;

import lombok.Data;

@Data
public class UpdateTimeslotTimeRangeRequest extends UpdateTimeslotRequest {
    private String startTime;
    private String endTime;
}
