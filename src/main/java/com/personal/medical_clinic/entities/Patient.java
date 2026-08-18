package com.personal.medical_clinic.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "tb_patient")
public class Patient implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotBlank
    private String name;

    @NotBlank
    @Pattern(regexp = "^\\d{10,11}$", message = "Telefone deve ter 10 ou 11 dígitos")
    @Column(nullable = false, unique = true)
    private String phone;

    @Email
    @Column(nullable = false, unique = true)
    @NotBlank
    private String email;


    @OneToMany(mappedBy = "patient")
    private Set<Appointments> ap = new HashSet<>();

    public Patient () { }

    public Patient(Long id, String name, String phone, String email) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getPhone() { return phone; }

    public void setPhone(String phone) { this.phone = phone; }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Patient patient = (Patient) o;
        return Objects.equals(id, patient.id) && Objects.equals(name, patient.name) && Objects.equals(phone, patient.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, phone);
    }
}
