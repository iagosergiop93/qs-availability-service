package com.booking.qs_availability_service.messages.timeslots.consumers;

import com.booking.qs_availability_service.dtos.staffavailability.StaffAvailabilityDto;
import com.booking.qs_availability_service.dtos.timeslots.CreateApptSlots;
import com.booking.qs_availability_service.dtos.timeslots.CreateVisitRequest;
import com.booking.qs_availability_service.messages.BaseMessage;
import com.booking.qs_availability_service.services.interfaces.TimeSlotsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
public class TimeslotConsumer {

    @Autowired
    private TimeSlotsService timeSlotsService;

    @Bean
    public Consumer<StaffAvailabilityDto> staffavailabilityupdated() {
        return message -> {
            System.out.println("Inside consumer staffAvailabilityUpdated: ");
            System.out.println(message.toString());
        };
    }

    @Bean
    public Consumer<BaseMessage<CreateApptSlots>> createtimeslotsrequested() {
        return message -> {
            System.out.println("Inside consumer createtimeslotsrequested: ");
            var response = timeSlotsService.createApptSlots(message.getPayload());
            if(response.isSuccess()) {
                System.out.println("Time Slots created successfully");
            }
            else {
                System.out.println("Failed to create Timeslots");
            }
        };
    }

    @Bean
    public Consumer<BaseMessage<CreateVisitRequest>> createvisitrequested() {
        return message -> {
            var response = timeSlotsService.reserveVisitTimeslot(message.getPayload());
            if(response.isSuccess()) {
                System.out.println("Timeslots reserved successfully");
            }
            else {
                System.out.println("Could not reserve Timeslots");
            }
        };
    }
}
