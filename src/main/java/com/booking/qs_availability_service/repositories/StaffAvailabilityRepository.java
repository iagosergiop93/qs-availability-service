package com.booking.qs_availability_service.repositories;

import com.booking.qs_availability_service.domain.staffavailability.StaffAvailability;
import com.booking.qs_availability_service.dtos.staffavailability.StaffAvailabilityDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StaffAvailabilityRepository extends JpaRepository<StaffAvailability,String> {

    @Query(
            nativeQuery = true,
            value = "SELECT id,orgId,locationId,startTime,endTime,date,availabilityType,availableStaffCount FROM staff_availability sa WHERE orgId = ?1 AND locationId = ?2"
    )
    List<StaffAvailabilityDto> listAvailability(String orgId, String locId);

}
