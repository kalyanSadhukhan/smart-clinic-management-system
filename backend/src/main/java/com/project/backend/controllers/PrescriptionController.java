package com.project.backend.controllers;

import com.project.backend.dto.ApiResponse;
import com.project.backend.models.Prescription;
import com.project.backend.repositories.PrescriptionRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/prescriptions")
public class PrescriptionController {

    @Autowired
    private PrescriptionRepository prescriptionRepository;

    @PostMapping
    @PreAuthorize("hasRole('DOCTOR')")
    public ResponseEntity<ApiResponse<Prescription>> createPrescription(@Valid @RequestBody Prescription prescription) {
        if (prescription.getPrescriptionDate() == null) {
            prescription.setPrescriptionDate(LocalDate.now());
        }
        Prescription saved = prescriptionRepository.save(prescription);
        return ResponseEntity.ok(new ApiResponse<>(true, "Prescription saved successfully", saved));
    }

    @GetMapping("/patient/{patientId}")
    @PreAuthorize("hasAnyRole('PATIENT', 'DOCTOR', 'ADMIN')")
    public ResponseEntity<ApiResponse<List<Prescription>>> getPatientPrescriptions(@PathVariable Long patientId) {
        List<Prescription> prescriptions = prescriptionRepository.findByPatientId(patientId);
        return ResponseEntity.ok(new ApiResponse<>(true, "Prescriptions retrieved", prescriptions));
    }
}
