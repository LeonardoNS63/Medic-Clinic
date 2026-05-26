package com.personal.medical_clinic.servicies;

import com.personal.medical_clinic.entities.Appointments;
import com.personal.medical_clinic.entities.Medic;
import com.personal.medical_clinic.entities.Patient;
import com.personal.medical_clinic.repository.AppointmentsRepository;
import com.personal.medical_clinic.repository.MedicRepository;
import com.personal.medical_clinic.repository.PatientRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

public class AppointmentService {

    @Autowired
    private MedicRepository medicRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private AppointmentsRepository appointmentsRepository;

    @Transactional
    public Appointments create(Appointments obj) {

        Medic medic = medicRepository.findById(obj.getMedic().getId())
                . orElseThrow(() -> new EntityNotFoundException("Médico não encontrado"));

        Patient patient = patientRepository.findById(obj.getPatient().getId())
                . orElseThrow(() -> new EntityNotFoundException("Paciente não encontrado"));

        obj.setDoctorName(medic.getName());
        obj.setPatientName(patient.getName());

        return appointmentsRepository.save(obj);
    }

}
