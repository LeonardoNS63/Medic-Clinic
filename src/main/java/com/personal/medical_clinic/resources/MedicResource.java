package com.personal.medical_clinic.resources;

import com.personal.medical_clinic.entities.Medic;
import com.personal.medical_clinic.entities.Patient;
import com.personal.medical_clinic.servicies.MedicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/doctors")
public class MedicResource {

    @Autowired
    private MedicService service;

    @GetMapping
    public ResponseEntity<List<Medic>> findAll() {
        List<Medic> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }
}
