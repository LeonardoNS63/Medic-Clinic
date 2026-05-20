package com.personal.medical_clinic.resources;

import com.personal.medical_clinic.entities.Patient;
import com.personal.medical_clinic.servicies.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/patients")
public class PatientResource {

    @Autowired
    private PatientService service;

    @GetMapping
    public ResponseEntity<List<Patient>> findAll() {
        List<Patient> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }
}
