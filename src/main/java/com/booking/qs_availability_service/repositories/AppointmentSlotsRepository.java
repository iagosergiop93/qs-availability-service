package com.booking.qs_availability_service.repositories;

import com.booking.qs_availability_service.domain.timeslots.AppointmentSlots;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentSlotsRepository extends JpaRepository<AppointmentSlots,String> {
}
