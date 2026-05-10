package com.project.backend.services;

import com.project.backend.dto.ApiResponse;
import com.project.backend.exceptions.ResourceNotFoundException;
import com.project.backend.models.Doctor;
import com.project.backend.repositories.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    public ApiResponse<List<String>> getAvailableSlots(Long doctorId, String date) {
        // Simple logic for returning available times. In a real app, it would check the date.
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found with id: " + doctorId));
                
        return new ApiResponse<>(true, "Available slots retrieved successfully", doctor.getAvailableTimes());
    }

    public ApiResponse<Doctor> addDoctor(Doctor doctor) {
        Doctor savedDoctor = doctorRepository.save(doctor);
        return new ApiResponse<>(true, "Doctor added successfully", savedDoctor);
    }

    public ApiResponse<List<Doctor>> getAllDoctors() {
        return new ApiResponse<>(true, "Doctors retrieved successfully", doctorRepository.findAll());
    }
    
    public ApiResponse<List<Doctor>> searchDoctorsBySpecialization(String spec) {
        return new ApiResponse<>(true, "Doctors found", doctorRepository.findBySpecializationContainingIgnoreCase(spec));
    }
}
