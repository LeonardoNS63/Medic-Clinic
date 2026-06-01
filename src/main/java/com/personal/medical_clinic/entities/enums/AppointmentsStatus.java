package com.personal.medical_clinic.entities.enums;


public enum AppointmentsStatus {

    COMPLETED(1),
    WAITING(2),
    CANCELED(3);

    private int code;

    private AppointmentsStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static AppointmentsStatus valueOf(int code) {
        for (AppointmentsStatus value : AppointmentsStatus.values()) {
            if (value.getCode() == code) {
                return value;
            }
        }
        throw new IllegalArgumentException("Invalid OrderStatus code");
    }

}
