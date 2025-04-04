package com.booking.qs_availability_service.dtos.timeslots;

import lombok.Data;

import java.util.List;

@Data
public class ListTimeslotsResponse {
    private String orgId;
    private String locationId;
    private List<TimeslotDto> timeslots;
}
