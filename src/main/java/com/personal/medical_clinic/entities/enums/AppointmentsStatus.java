package com.personal.medical_clinic.entities.enums;


public enum AppointmentsStatus {

    COMPLETED("COMPLETED"),
    WAITING("WAITING"),
    CANCELED("CANCELED");

    private String state;

    AppointmentsStatus(String state) {this.state = state;}

    public String getState() {
        return state;
    }

}
