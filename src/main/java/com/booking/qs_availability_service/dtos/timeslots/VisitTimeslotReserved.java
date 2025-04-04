package com.booking.qs_availability_service.dtos.timeslots;

import com.booking.qs_availability_service.dtos.Message;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class VisitTimeslotReserved {

    private String id;
    private String orgId;
    private String locationId;
    private String visitTypeCode;

    private Boolean slotReserved;

    private List<String> timeSlotIds;

    public VisitTimeslotReserved(CreateVisitRequest cvr) {
        this.id = cvr.getId();
        this.orgId = cvr.getOrgId();
        this.locationId = cvr.getLocationId();
        this.visitTypeCode = cvr.getVisitTypeCode();
    }
}
