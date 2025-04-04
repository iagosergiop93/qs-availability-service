package com.booking.qs_availability_service.domain.staffavailability;

public enum StaffAvailabilityType {
    ALL_WEEK("all_week"),
    WEEKDAYS("weekdays"),
    WEEKENDS("weekends"),
    TEMP_HOURS("temp_hours");

    private final String value;

    StaffAvailabilityType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static StaffAvailabilityType fromValue(String value) {
        for (StaffAvailabilityType sat : values()) {
            if (sat.value.equalsIgnoreCase(value)) {
                return sat;
            }
        }
        throw new IllegalArgumentException("Unknown database value: " + value);
    }
}
