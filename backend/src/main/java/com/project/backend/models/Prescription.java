package com.project.backend.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDate;

@Entity
public class Prescription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "appointment_id", nullable = false)
    private Appointment appointment;

    @NotBlank(message = "Diagnosis is required")
    private String diagnosis;

    @NotBlank(message = "Medication is required")
    private String medication;

    private LocalDate prescriptionDate = LocalDate.now();

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Doctor getDoctor() { return doctor; }
    public void setDoctor(Doctor doctor) { this.doctor = doctor; }
    public Patient getPatient() { return patient; }
    public void setPatient(Patient patient) { this.patient = patient; }
    public Appointment getAppointment() { return appointment; }
    public void setAppointment(Appointment appointment) { this.appointment = appointment; }
    public String getDiagnosis() { return diagnosis; }
    public void setDiagnosis(String diagnosis) { this.diagnosis = diagnosis; }
    public String getMedication() { return medication; }
    public void setMedication(String medication) { this.medication = medication; }
    public LocalDate getPrescriptionDate() { return prescriptionDate; }
    public void setPrescriptionDate(LocalDate prescriptionDate) { this.prescriptionDate = prescriptionDate; }
}
