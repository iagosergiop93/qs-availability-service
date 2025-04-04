package com.booking.qs_availability_service.domain.timeslots;

import com.booking.qs_availability_service.domain.AbstractEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Entity
@Table(name = "timeslots")
public class Timeslot extends AbstractEntity<String> {

    @Id
    private String id;
    private String orgId;
    private String locationId;
    private LocalDate day;
    private Integer slotDurationMin;
    private LocalTime startTime;
    private LocalTime endTime;
    private Integer bookings;
    private Integer totalAvailableStaff;
}
