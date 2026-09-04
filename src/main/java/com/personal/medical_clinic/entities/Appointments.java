package com.personal.medical_clinic.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

@Entity
@Table(name = "tb_medical_appointments")
public class Appointments implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Instant moment;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne
    @OnDelete(action = OnDeleteAction.SET_NULL)
    @JoinColumn(name = "medic_id", nullable = true)
    private Medic medic;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne
    @OnDelete(action = OnDeleteAction.SET_NULL)
    @JoinColumn(name = "patient_id", nullable = true)
    private Patient patient;

    private String doctorName;
    private String patientName;

    public Appointments(){ }

    public Appointments(Long id, Instant moment, Medic medic, Patient patient) {
        this.id = id;
        this.moment = moment;
        this.medic = medic;
        this.patient = patient;
        this.doctorName = medic != null ? medic.getName() : null;
        this.patientName = patient != null ? patient.getName() : null;
    }

    public Instant getMoment() { return moment; }

    public void setMoment(Instant moment) { this.moment = moment; }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Medic getMedic() {
        return medic;
    }

    public void setMedic(Medic medic) {
        this.medic = medic;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Appointments that = (Appointments) o;
        return Objects.equals(id, that.id) && Objects.equals(moment, that.moment) && Objects.equals(medic, that.medic) && Objects.equals(patient, that.patient) && Objects.equals(doctorName, that.doctorName) && Objects.equals(patientName, that.patientName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, moment, medic, patient, doctorName, patientName);
    }
}
