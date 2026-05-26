package com.personal.medical_clinic.resources;


import com.personal.medical_clinic.entities.Appointments;
import com.personal.medical_clinic.entities.Medic;
import com.personal.medical_clinic.servicies.AppointmentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/appointments")
public class AppointmentsResource {

    @Autowired
    private AppointmentsService service;

    @GetMapping
    public ResponseEntity<List<Appointments>> findAll() {
        List<Appointments> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Appointments> findById(@PathVariable Long id) {
        Appointments obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Appointments> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Appointments> update(@PathVariable Long id, @RequestBody Appointments obj) {
        obj = service.update(id, obj);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Appointments obj) {
        try {
            Appointments result = service.create(obj);
            return ResponseEntity.ok(result);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


}
