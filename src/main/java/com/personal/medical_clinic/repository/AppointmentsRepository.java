package com.personal.medical_clinic.repository;

import com.personal.medical_clinic.entities.Appointments;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentsRepository extends JpaRepository<Appointments, Long> { }
