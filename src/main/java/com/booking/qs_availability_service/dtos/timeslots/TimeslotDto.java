package com.booking.qs_availability_service.dtos.timeslots;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
public class TimeslotDto {
    private String id;
    private Integer slotDurationMin;
    private LocalDate day;
    private LocalTime startTime;
    private LocalTime endTime;

    public TimeslotDto(String id, Date day, Time startTime, Time endTime, Integer slotDurationMin) {
        this.id = id;
        this.slotDurationMin = slotDurationMin;
        this.day = day.toLocalDate();
        this.startTime = startTime.toLocalTime();
        this.endTime = endTime.toLocalTime();
    }
}
