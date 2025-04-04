package com.booking.qs_availability_service.repositories;

import com.booking.qs_availability_service.domain.timeslots.Timeslot;
import com.booking.qs_availability_service.dtos.timeslots.TimeslotDto;
import jakarta.persistence.QueryHint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Stream;

@Repository
public interface TimeslotRepository extends JpaRepository<Timeslot,String> {

    @QueryHints(@QueryHint(name = "org.hibernate.fetchSize", value = "200"))
    @Query(
            value = "SELECT * " +
                    "FROM timeslots " +
                    "WHERE orgId = ?1 AND locationId = ?2 AND day between ?3 AND ?4 " +
                    "ORDER BY day,startTime asc",
            nativeQuery = true
    )
    Stream<Timeslot> queryTimeSlotsByDateRange(String orgId, String locId, String startDate, String endDate);

    @QueryHints(@QueryHint(name = "org.hibernate.fetchSize", value = "200"))
    @Query(
            value = "SELECT id, day, startTime, endTime, slotDurationMin " +
                    "FROM timeslots " +
                    "WHERE orgId = ?1 AND locationId = ?2 AND day between ?3 AND ?4 " +
                    "AND bookings < totalAvailableStaff " +
                    "ORDER BY day,startTime asc",
            nativeQuery = true
    )
    List<TimeslotDto> queryAvailableSlotsByDateRange(String orgId, String locId, String startDate, String endDate);

    @QueryHints(@QueryHint(name = "org.hibernate.fetchSize", value = "200"))
    @Query(
            value = "SELECT * " +
                    "FROM timeslots " +
                    "WHERE orgId = ?1 AND locationId = ?2 AND day = ?3 " +
                    "AND startTime >= ?4 " +
                    "AND endTime <= ?5 " +
                    "ORDER BY day,startTime asc",
            nativeQuery = true
    )
    List<Timeslot> queryAvailableSlotsByDateAndTime(String orgId, String locId, String day, String startTime, String endTime);
}
