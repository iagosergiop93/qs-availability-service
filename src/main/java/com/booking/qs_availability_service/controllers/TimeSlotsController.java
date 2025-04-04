package com.booking.qs_availability_service.controllers;

import com.booking.qs_availability_service.domain.timeslots.AppointmentSlots;
import com.booking.qs_availability_service.dtos.Response;
import com.booking.qs_availability_service.dtos.timeslots.CreateApptSlots;
import com.booking.qs_availability_service.dtos.timeslots.ListTimeslotsRequest;
import com.booking.qs_availability_service.dtos.timeslots.ListTimeslotsResponse;
import com.booking.qs_availability_service.services.interfaces.TimeSlotsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TimeSlotsController {

    @Autowired
    private TimeSlotsService service;

    @PostMapping("/api/timeslots/create")
    public Response<AppointmentSlots> createApptSlots(@RequestBody CreateApptSlots request) {
        return service.createApptSlots(request);
    }

    @PostMapping("/api/timeslots/available/list")
    public Response<ListTimeslotsResponse> listAvailableTimeslots(@RequestBody ListTimeslotsRequest request) {
        return service.listAvailableTimeslots(request);
    }

}
