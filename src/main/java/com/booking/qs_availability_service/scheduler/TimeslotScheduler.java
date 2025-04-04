package com.booking.qs_availability_service.scheduler;

import com.booking.qs_availability_service.repositories.StaffAvailabilityRepository;
import com.booking.qs_availability_service.repositories.TimeslotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TimeslotScheduler {

    @Autowired
    private StaffAvailabilityRepository staffAvailabilityRepo;

    @Autowired
    private TimeslotRepository timeslotRepo;
    
    public void clearOldTimeslots() {

    }

    public void createNewTimeslots() {

    }

}
