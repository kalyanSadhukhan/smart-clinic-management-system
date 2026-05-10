package com.project.backend.security;

import com.project.backend.models.Admin;
import com.project.backend.models.Doctor;
import com.project.backend.models.Patient;
import com.project.backend.repositories.AdminRepository;
import com.project.backend.repositories.DoctorRepository;
import com.project.backend.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private PatientRepository patientRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Admin> admin = adminRepository.findByEmail(email);
        if (admin.isPresent()) {
            return new CustomUserDetails(admin.get().getEmail(), admin.get().getPassword(), admin.get().getRole());
        }

        Optional<Doctor> doctor = doctorRepository.findByEmail(email);
        if (doctor.isPresent()) {
            return new CustomUserDetails(doctor.get().getEmail(), doctor.get().getPassword(), doctor.get().getRole());
        }

        Optional<Patient> patient = patientRepository.findByEmail(email);
        if (patient.isPresent()) {
            return new CustomUserDetails(patient.get().getEmail(), patient.get().getPassword(), patient.get().getRole());
        }

        throw new UsernameNotFoundException("User not found with email: " + email);
    }
}
