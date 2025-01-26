package com.booking.qs_availability_service.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.domain.Persistable;

import java.time.Instant;

@MappedSuperclass
@Data
public abstract class AbstractEntity<ID> implements Persistable<ID> {

    @JsonIgnore
    Instant createdAt;
    @JsonIgnore
    Instant updatedAt;

    @Transient
    @JsonIgnore
    private boolean isNew = false;

    @Override
    public boolean isNew() {
        return isNew;
    }

    @PrePersist
    public void markNotNew() {
        this.isNew = false;
        createdAt = Instant.now();
        updatedAt = Instant.now();
    }

    @PreUpdate
    public void onUpdate() {
        updatedAt = Instant.now();
    }

    @PostLoad
    public void postload() {
        this.isNew = false;
    }
}

