package com.personal.medical_clinic.servicies;


import com.personal.medical_clinic.entities.Medic;
import com.personal.medical_clinic.repository.MedicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicService {

    @Autowired
    private MedicRepository repository;

    public List<Medic> findAll() {
        return repository.findAll();
    }
}