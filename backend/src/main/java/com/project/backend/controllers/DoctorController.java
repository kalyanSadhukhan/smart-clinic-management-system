package com.project.backend.controllers;

import com.project.backend.dto.ApiResponse;
import com.project.backend.models.Doctor;
import com.project.backend.services.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctors")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @GetMapping("/{id}/availability")
    public ResponseEntity<ApiResponse<List<String>>> getDoctorAvailability(
            @PathVariable Long id,
            @RequestParam(required = false, defaultValue = "today") String date) {
        return ResponseEntity.ok(doctorService.getAvailableSlots(id, date));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Doctor>>> getAllDoctors() {
        return ResponseEntity.ok(doctorService.getAllDoctors());
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<Doctor>>> searchDoctors(@RequestParam String specialization) {
        return ResponseEntity.ok(doctorService.searchDoctorsBySpecialization(specialization));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<Doctor>> addDoctor(@RequestBody Doctor doctor) {
        return ResponseEntity.ok(doctorService.addDoctor(doctor));
    }
}
