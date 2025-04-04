package com.booking.qs_availability_service.domain.staffavailability;

import com.booking.qs_availability_service.domain.AbstractEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;

@Data
@Entity
@Table(name = "staff_availability")
public class StaffAvailability extends AbstractEntity<String> {
    @Id
    private String id;
    private String orgId;
    private String locationId;
    private LocalTime startTime;
    private LocalTime endTime;
    private LocalDate date;

    @Enumerated(EnumType.STRING)
    private StaffAvailabilityType availabilityType;
    private Integer availableStaffCount;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        StaffAvailability that = (StaffAvailability) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id);
    }
}
