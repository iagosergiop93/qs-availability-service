package com.booking.qs_availability_service.messages.timeslots.producers;

import com.booking.qs_availability_service.dtos.Message;
import com.booking.qs_availability_service.dtos.timeslots.VisitTimeslotReserved;
import com.booking.qs_availability_service.messages.BaseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Component;
import org.springframework.util.MimeType;

@Component
public class TimeslotProducer {

    @Autowired
    private StreamBridge streamBridge;

    public Boolean visitTimeslotReserved(VisitTimeslotReserved vtr, Message errorMessage) {
        var baseMessage = new BaseMessage<>(vtr);
//        baseMessage.setErrorMessage(errorMessage);
        var message = new GenericMessage<>(baseMessage);
        return streamBridge.send("visit-timeslot-reserved", message, MimeType.valueOf("application/json"));
    }
}
