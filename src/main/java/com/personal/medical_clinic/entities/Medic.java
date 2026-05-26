package com.personal.medical_clinic.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "tb_medic")
public class Medic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    @NotBlank
    private String name;
    private String specialization;
    @Column(nullable = false)
    private String phone;

    @OneToMany(mappedBy = "medic")
    private Set<Appointments> ap = new HashSet<>();

    public Medic() { }

    public Medic(Long id, String name, String specialization, String phone) {
        this.id = id;
        this.name = name;
        this.specialization = specialization;
        this.phone = phone;
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getSpecialization() { return specialization; }

    public void setSpecialization(String specialization) { this.specialization = specialization; }

    public String getPhone() { return phone; }

    public void setPhone(String phone) { this.phone = phone; }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Medic medic = (Medic) o;
        return Objects.equals(id, medic.id) && Objects.equals(name, medic.name) && Objects.equals(specialization, medic.specialization) && Objects.equals(phone, medic.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, specialization, phone);
    }
}
