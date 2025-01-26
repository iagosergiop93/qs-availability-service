package com.booking.qs_availability_service.controllers;

import com.booking.qs_availability_service.domain.staffavailability.StaffAvailability;
import com.booking.qs_availability_service.dtos.Response;
import com.booking.qs_availability_service.dtos.staffavailability.ListAvailabilityByLocationRequest;
import com.booking.qs_availability_service.dtos.staffavailability.StaffAvailabilityDto;
import com.booking.qs_availability_service.services.interfaces.StaffAvailabilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StaffAvailabilityController {

    @Autowired
    private StaffAvailabilityService service;

    @GetMapping("/api/availability/schedule/org/{orgId}/location/{locId}")
    public Response<List<StaffAvailabilityDto>> listSchedule(
            @PathVariable String orgId,
            @PathVariable String locId,
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate
    ) {
        var request = new ListAvailabilityByLocationRequest();
        request.setOrgId(orgId);
        request.setLocationId(locId);
        request.setStartDate(startDate);
        request.setEndDate(endDate);
        return service.listAvailabilityByLocation(request);
    }

    @PostMapping("/api/availability")
    public Response<StaffAvailabilityDto> addStaffAvailability(@RequestBody StaffAvailabilityDto request) {
        return service.addStaffAvailability(request);
    }
}
