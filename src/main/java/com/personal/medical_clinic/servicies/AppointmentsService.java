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
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentsService {

    @Autowired
    private MedicRepository medicRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private AppointmentsRepository appointmentsRepository;

    public List<Appointments> findAll() { return appointmentsRepository.findAll(); }

    public Appointments findById(Long id) {
        Optional<Appointments> obj = appointmentsRepository.findById(id);
        return obj.get();
    }

    public void delete(Long id) { appointmentsRepository.deleteById(id); }

    public Appointments update(Long id, Appointments obj) {
        Appointments entity = appointmentsRepository.getReferenceById(id);
        validateConflict(obj.getMedic().getId(), obj.getPatient().getId(), obj.getMoment(), id);
        updateData(entity, obj);
        return appointmentsRepository.save(entity);
    }

    private void updateData(Appointments entity, Appointments obj) {
        entity.setMedic(obj.getMedic());
        entity.setPatient(obj.getPatient());
        entity.setMoment(obj.getMoment());
        entity.setDoctorName(obj.getDoctorName());
        entity.setPatientName(obj.getPatientName());
    }

    @Transactional
    public Appointments create(Appointments obj) {

        Medic medic = medicRepository.findById(obj.getMedic().getId())
                .orElseThrow(() -> new EntityNotFoundException("Médico não encontrado"));

        Patient patient = patientRepository.findById(obj.getPatient().getId())
                .orElseThrow(() -> new EntityNotFoundException("Paciente não encontrado"));

        validateConflict(obj.getMedic().getId(), obj.getPatient().getId(), obj.getMoment(), null);

        obj.setDoctorName(medic.getName());
        obj.setPatientName(patient.getName());

        return appointmentsRepository.save(obj);
    }

    private void validateConflict(Long medicId, Long patientId, Instant moment, Long excludeId) {
        Instant start = moment.minus(30, ChronoUnit.MINUTES);
        Instant end = moment.plus(30, ChronoUnit.MINUTES);

        boolean conflict = appointmentsRepository.existsConflict(
                medicId,
                patientId,
                start,
                end,
                excludeId
        );

        if (conflict) {
            throw new IllegalArgumentException(
                    "Médico ou paciente já possui consulta neste intervalo de 30 minutos."
            );
        }
    }

}
