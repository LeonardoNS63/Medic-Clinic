package com.personal.medical_clinic.config;

import com.personal.medical_clinic.entities.Appointments;
import com.personal.medical_clinic.entities.Medic;
import com.personal.medical_clinic.entities.Patient;
import com.personal.medical_clinic.repository.AppointmentsRepository;
import com.personal.medical_clinic.repository.MedicRepository;
import com.personal.medical_clinic.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
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

        Patient p1 = new Patient(null, "Leandro", "11 99999-9999");
        Patient p2 = new Patient(null, "Xuxa", "10 99999-9888");

        patientRepository.saveAll((Arrays.asList(p1, p2)));

        Medic m1 = new Medic(null, "Igor", "Dermatologista", "12 88888-8888");
        Medic m2 = new Medic(null, "Carlos", "Cardiologista", "12 11188-8888");

        medicRepository.saveAll((Arrays.asList(m1, m2)));

        Appointments ap1 = new Appointments(null, LocalDate.parse("2019-06-20"), LocalTime.parse("15:30:00"), m1, p2);
        Appointments ap2 = new Appointments(null, LocalDate.parse("2019-06-10"),LocalTime.parse("15:30:00"), m2, p2);
        Appointments ap3 = new Appointments(null, LocalDate.parse("2019-06-20"), LocalTime.parse ("16:00:00"), m1, p1);

        appointmentsRepository.saveAll((Arrays.asList(ap1, ap2, ap3)));

    }


}
