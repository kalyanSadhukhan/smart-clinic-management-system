# API Documentation

## Auth Controller
- `POST /api/auth/login` - Login for Admin/Doctor/Patient.
- `POST /api/auth/patient/login` - Explicit Patient Login.

## Doctor Controller
- `GET /api/doctors` - Get all doctors.
- `GET /api/doctors/{id}/availability` - Get available slots.
- `GET /api/doctors/search?specialization={spec}` - Search doctors.
- `POST /api/doctors` - Add a doctor (Admin only).

## Appointment Controller
- `POST /api/appointments` - Book appointment.
- `GET /api/appointments/doctor/{id}?date={date}` - Get doctor's appointments.
- `GET /api/appointments` - Get all appointments (Admin).

## Prescription Controller
- `POST /api/prescriptions` - Create prescription (Doctor only).
- `GET /api/prescriptions/patient/{id}` - Get patient's prescriptions.
