package com.personal.medical_clinic.config;

import com.personal.medical_clinic.repository.AppointmentsRepository;
import com.personal.medical_clinic.repository.MedicRepository;
import com.personal.medical_clinic.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.Instant;
import java.util.Arrays;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private MedicRepository medicRepository;

    @Autowired
    private AppointmentsRepository appointmentsRepository;

    @Override
    public void run(String... args) throws Exception {


    }


}
