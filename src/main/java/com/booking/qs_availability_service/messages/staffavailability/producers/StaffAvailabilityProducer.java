package com.booking.qs_availability_service.messages.staffavailability.producers;

import com.booking.qs_availability_service.dtos.staffavailability.StaffAvailabilityDto;
import com.booking.qs_availability_service.messages.BaseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Component;

@Component
public class StaffAvailabilityProducer {

    @Autowired
    private StreamBridge streamBridge;

    public Boolean staffAvailabilityUpdatedProducer(StaffAvailabilityDto dto) {
        var baseMessage = new BaseMessage<StaffAvailabilityDto>(dto);
        var message = new GenericMessage<>(baseMessage);
        return streamBridge.send("staff-availability-updated", message);
    }

}

