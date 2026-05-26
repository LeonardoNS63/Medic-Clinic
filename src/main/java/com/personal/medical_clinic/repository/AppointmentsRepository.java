package com.personal.medical_clinic.repository;

import com.personal.medical_clinic.entities.Appointments;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalTime;


public interface AppointmentsRepository extends JpaRepository<Appointments, Long> {

    boolean existsByMedicIdAndDateAndTime(Long medicId, LocalDate date, LocalTime time);

}
