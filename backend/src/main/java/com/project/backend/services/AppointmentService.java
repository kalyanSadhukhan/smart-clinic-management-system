package com.project.backend.services;

import com.project.backend.dto.ApiResponse;
import com.project.backend.exceptions.ResourceNotFoundException;
import com.project.backend.models.Appointment;
import com.project.backend.repositories.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    public ApiResponse<Appointment> saveAppointmentBooking(Appointment appointment) {
        appointment.setStatus("SCHEDULED");
        Appointment savedAppointment = appointmentRepository.save(appointment);
        return new ApiResponse<>(true, "Appointment booked successfully", savedAppointment);
    }

    public ApiResponse<List<Appointment>> getDoctorAppointmentsByDate(Long doctorId, LocalDate date) {
        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = date.plusDays(1).atStartOfDay();
        List<Appointment> appointments = appointmentRepository.findByDoctorIdAndAppointmentTimeBetween(doctorId, startOfDay, endOfDay);
        return new ApiResponse<>(true, "Appointments retrieved successfully", appointments);
    }
    
    public ApiResponse<List<Appointment>> getAllAppointments() {
        return new ApiResponse<>(true, "Appointments retrieved successfully", appointmentRepository.findAll());
    }
}
