package com.booking.qs_availability_service.domain.timeslots;

import com.booking.qs_availability_service.domain.AbstractEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.Objects;

@Data
@Entity
@Table(name = "appointment_slots")
public class AppointmentSlots extends AbstractEntity<String> {
    @Id
    private String id;
    private String orgId;
    private String locationId;
    private String appointmentType;
    private Integer slotDurationMinutes;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        AppointmentSlots that = (AppointmentSlots) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id);
    }
}
