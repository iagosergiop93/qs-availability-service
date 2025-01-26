package com.booking.qs_availability_service.dtos.staffavailability;

import lombok.Data;

@Data
public class ListAvailabilityByLocationRequest {
    private String orgId;
    private String locationId;
    private String startDate;
    private String endDate;
}
