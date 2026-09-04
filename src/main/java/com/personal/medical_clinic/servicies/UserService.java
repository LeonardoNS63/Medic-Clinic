package com.personal.medical_clinic.servicies;

import com.personal.medical_clinic.dto.CadastroDTO;
import com.personal.medical_clinic.entities.Medic;
import com.personal.medical_clinic.entities.Patient;
import com.personal.medical_clinic.entities.User;
import com.personal.medical_clinic.entities.enums.Role;
import com.personal.medical_clinic.repository.MedicRepository;
import com.personal.medical_clinic.repository.PatientRepository;
import com.personal.medical_clinic.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository usuarioRepository;
    @Autowired
    private MedicRepository medicRepository;
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return (UserDetails) usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + email));
    }

    public User cadastrar(CadastroDTO dados) {
        if (usuarioRepository.existsByEmail(dados.email())) {
            throw new IllegalArgumentException("Email já cadastrado.");
        }

        User novoUsuario = new User();
        novoUsuario.setNome(dados.nome());
        novoUsuario.setEmail(dados.email());
        novoUsuario.setSenha(passwordEncoder.encode(dados.senha()));
        novoUsuario.setPhone(dados.telefone());
        novoUsuario.setRole(dados.role());

        User usuarioSalvo = usuarioRepository.save(novoUsuario);

        if (dados.role() == Role.MEDICO) {
            Medic doctor = new Medic();
            doctor.setUsuario(usuarioSalvo);
            medicRepository.save(doctor);

        } else if (dados.role() == Role.PACIENTE) {
            Patient patient = new Patient();
            patient.setUsuario(usuarioSalvo);
            patientRepository.save(patient);
        }

        return usuarioSalvo;
    }


}
