package com.personal.medical_clinic.entities.enums;



public enum Role {

    ADMIN("ROLE_ADMIN"),
    MEDICO("ROLE_MEDICO"),
    PACIENTE("ROLE_PACIENTE");

    private final String valor;

    Role(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }
}
