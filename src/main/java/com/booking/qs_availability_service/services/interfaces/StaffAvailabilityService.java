package com.booking.qs_availability_service.services.interfaces;

import com.booking.qs_availability_service.dtos.Response;
import com.booking.qs_availability_service.dtos.staffavailability.ListAvailabilityByLocationRequest;
import com.booking.qs_availability_service.dtos.staffavailability.StaffAvailabilityDto;

import java.util.List;


public interface StaffAvailabilityService {
    Response<StaffAvailabilityDto> upsertStaffAvailability(StaffAvailabilityDto request);
    Response<List<StaffAvailabilityDto>> listAvailabilityByLocation(ListAvailabilityByLocationRequest request);
}
