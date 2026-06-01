package com.personal.medical_clinic.repository;

import com.personal.medical_clinic.entities.Appointments;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.Instant;



public interface AppointmentsRepository extends JpaRepository<Appointments, Long> {

    @Query("""
    SELECT COUNT(a) > 0 FROM Appointments a
    WHERE (a.medic.id = :medicId OR a.patient.id = :patientId)
    AND a.moment > :start
    AND a.moment < :end
    AND (:excludeId IS NULL OR a.id <> :excludeId)
    """)
    boolean existsConflict(
            @Param("medicId") Long medicId,
            @Param("patientId") Long patientId,
            @Param("start") Instant start,
            @Param("end") Instant end,
            @Param("excludeId") Long excludeId
    );
}
