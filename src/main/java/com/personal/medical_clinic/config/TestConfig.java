package com.personal.medical_clinic.config;

import com.personal.medical_clinic.entities.Appointments;
import com.personal.medical_clinic.entities.Medic;
import com.personal.medical_clinic.entities.Patient;
import com.personal.medical_clinic.entities.enums.AppointmentsStatus;
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

        Patient p1 = new Patient(null, "Leandro", "11999999999", "leandro123@gmail.com");
        Patient p2 = new Patient(null, "Xuxa", "10999999998", "xuxa@hotmail.com");

        patientRepository.saveAll((Arrays.asList(p1, p2)));

        Medic m1 = new Medic(null, "Igor", "Dermatologista", "12888888888", "doutor.igor@gmail.com");
        Medic m2 = new Medic(null, "Carlos", "Cardiologista", "12111888888", "doutor.carlos@hotmail.com");

        medicRepository.saveAll((Arrays.asList(m1, m2)));

        Appointments ap1 = new Appointments(null, Instant.parse("2019-06-20T15:30:00Z"), m1, p2, AppointmentsStatus.CANCELED);
        Appointments ap2 = new Appointments(null, Instant.parse("2019-06-10T15:30:00Z"), m2, p2, AppointmentsStatus.COMPLETED);
        Appointments ap3 = new Appointments(null, Instant.parse("2019-06-20T16:00:00Z"), m1, p1, AppointmentsStatus.WAITING);

        appointmentsRepository.saveAll((Arrays.asList(ap1, ap2, ap3)));

    }


}
