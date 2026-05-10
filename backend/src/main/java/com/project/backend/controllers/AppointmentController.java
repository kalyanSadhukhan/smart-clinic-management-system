package com.project.backend.controllers;

import com.project.backend.dto.ApiResponse;
import com.project.backend.models.Appointment;
import com.project.backend.services.AppointmentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @PostMapping
    @PreAuthorize("hasAnyRole('PATIENT', 'ADMIN')")
    public ResponseEntity<ApiResponse<Appointment>> bookAppointment(@Valid @RequestBody Appointment appointment) {
        return ResponseEntity.ok(appointmentService.saveAppointmentBooking(appointment));
    }

    @GetMapping("/doctor/{doctorId}")
    @PreAuthorize("hasAnyRole('DOCTOR', 'ADMIN')")
    public ResponseEntity<ApiResponse<List<Appointment>>> getAppointmentsByDate(
            @PathVariable Long doctorId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return ResponseEntity.ok(appointmentService.getDoctorAppointmentsByDate(doctorId, date));
    }
    
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<List<Appointment>>> getAllAppointments() {
        return ResponseEntity.ok(appointmentService.getAllAppointments());
    }
}
