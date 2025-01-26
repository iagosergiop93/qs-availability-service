package com.booking.qs_availability_service.domain.staffavailability;

import com.booking.qs_availability_service.domain.AbstractEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.Instant;
import java.util.Objects;

@Data
@Entity
@Table(name = "staff_availability")
public class StaffAvailability extends AbstractEntity<String> {
    @Id
    private String id;
    private String orgId;
    private String locationId;
    private String startTime;
    private String endTime;
    private Instant date;
    private String availabilityType;
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
