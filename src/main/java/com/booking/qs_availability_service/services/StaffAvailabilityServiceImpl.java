package com.booking.qs_availability_service.services;

import com.booking.qs_availability_service.domain.staffavailability.StaffAvailability;
import com.booking.qs_availability_service.dtos.Message;
import com.booking.qs_availability_service.dtos.MessageType;
import com.booking.qs_availability_service.dtos.Response;
import com.booking.qs_availability_service.dtos.staffavailability.AddStaffAvailabilityRequest;
import com.booking.qs_availability_service.dtos.staffavailability.ListAvailabilityByLocationRequest;
import com.booking.qs_availability_service.dtos.staffavailability.StaffAvailabilityDto;
import com.booking.qs_availability_service.repositories.StaffAvailabilityRepository;
import com.booking.qs_availability_service.services.interfaces.StaffAvailabilityService;
import com.booking.qs_availability_service.utils.StaffAvailabilityUtils;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StaffAvailabilityServiceImpl implements StaffAvailabilityService {

    @Autowired
    private StaffAvailabilityRepository repo;

    @Override
    public Response<StaffAvailabilityDto> addStaffAvailability(StaffAvailabilityDto request) {
        var response = new Response<StaffAvailabilityDto>();
        try {
            var availability = StaffAvailabilityUtils.createStaffAvailability(request);
            availability.setNew(true);
            availability =  repo.saveAndFlush(availability);
            request.setId(availability.getId());
            response
                    .withSuccess(true)
                    .with(request);
        } catch (Throwable e) {
            response
                    .withSuccess(false)
                    .addMessage(new Message("500", e.getMessage()));
        }
        return response;
    }

    @Override
    public Response<List<StaffAvailabilityDto>> listAvailabilityByLocation(ListAvailabilityByLocationRequest request) {
        var response = new Response<List<StaffAvailabilityDto>>();
        try {
            var list = repo.listAvailability(request.getOrgId(), request.getLocationId());
            if(list == null || list.isEmpty()) {
                response.addMessage(new Message("400", "No data found", MessageType.INFO));
            }
            else {
                response.with(list).withSuccess(true);
            }
        } catch (Throwable e) {
            response
                    .withSuccess(false)
                    .addMessage(new Message("500", e.getMessage()));
        }
        return response;
    }
}
