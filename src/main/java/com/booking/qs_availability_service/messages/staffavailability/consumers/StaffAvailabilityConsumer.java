package com.booking.qs_availability_service.messages.staffavailability.consumers;

import com.booking.qs_availability_service.dtos.staffavailability.StaffAvailabilityDto;
import com.booking.qs_availability_service.messages.BaseMessage;
import com.booking.qs_availability_service.services.interfaces.StaffAvailabilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
public class StaffAvailabilityConsumer {

    @Autowired
    StaffAvailabilityService staffAvailabilityService;

    @Bean
    public Consumer<BaseMessage<StaffAvailabilityDto>> staffAvailabilityRequested() {
        return message -> {
            System.out.println("Inside consumer staffAvailabilityRequested: ");
            System.out.println(message.toString());
            var res = staffAvailabilityService.upsertStaffAvailability(message.getPayload());
        };
    }

}
