package com.personal.medical_clinic.servicies;


import com.personal.medical_clinic.entities.Medic;
import com.personal.medical_clinic.repository.MedicRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedicService {

    @Autowired
    private MedicRepository repository;

    public List<Medic> findAll() {
        return repository.findAll();
    }

    public Medic findById(Long id) {
        Optional<Medic> obj = repository.findById(id);
        return obj.get();
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Transactional
    public Medic update(Long id, Medic obj) {
        Medic entity = repository.getReferenceById(id);
        updateData(entity, obj);
        return repository.save(entity);
    }

    private void updateData(Medic entity, Medic obj) {
        entity.setName(obj.getName());
        entity.setSpecialization(obj.getSpecialization());
        entity.setPhone(obj.getPhone());
    }

    public Medic insert(Medic obj) {
        return repository.save(obj);
    }
}