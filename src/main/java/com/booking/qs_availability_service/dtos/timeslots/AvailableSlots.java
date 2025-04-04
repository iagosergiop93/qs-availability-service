package com.booking.qs_availability_service.dtos.timeslots;

import com.booking.qs_availability_service.domain.timeslots.Timeslot;
import lombok.Data;

import java.util.List;

@Data
public class AvailableSlots {
    private String id;
    private String orgId;
    private String locationId;
    private Boolean slotsAvailable;
    private List<Timeslot> timeslots;
}
