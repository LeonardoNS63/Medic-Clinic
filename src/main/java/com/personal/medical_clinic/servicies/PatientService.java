package com.personal.medical_clinic.servicies;

import com.personal.medical_clinic.entities.Patient;
import com.personal.medical_clinic.repository.PatientRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

    @Autowired
    private PatientRepository repository;

    public List<Patient> findAll() { return repository.findAll(); }

    public Patient findById(Long id) {
        Optional<Patient> obj = repository.findById(id);
        return obj.get();
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Transactional
    public Patient update(Long id, Patient obj) {
        Patient entity = repository.getReferenceById(id);
        updateData(entity, obj);
        return repository.save(entity);
    }

    private void updateData(Patient entity, Patient obj) {
        entity.setName(obj.getName());
        entity.setPhone(obj.getPhone());
    }

    public Patient insert(Patient obj) {
        return repository.save(obj);
    }
}
