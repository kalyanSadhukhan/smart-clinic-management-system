# Schema Design

## ER Diagram (Textual)

### 1. `admin`
- `id` (PK, BIGINT)
- `name` (VARCHAR)
- `email` (VARCHAR, UNIQUE)
- `password` (VARCHAR)
- `role` (VARCHAR)

### 2. `doctor`
- `id` (PK, BIGINT)
- `name` (VARCHAR)
- `email` (VARCHAR, UNIQUE)
- `password` (VARCHAR)
- `specialization` (VARCHAR)
- `role` (VARCHAR)

### 3. `patient`
- `id` (PK, BIGINT)
- `name` (VARCHAR)
- `email` (VARCHAR, UNIQUE)
- `password` (VARCHAR)
- `phone` (VARCHAR)
- `address` (VARCHAR)
- `role` (VARCHAR)

### 4. `appointment`
- `id` (PK, BIGINT)
- `doctor_id` (FK to doctor.id)
- `patient_id` (FK to patient.id)
- `appointment_time` (DATETIME)
- `status` (VARCHAR)

### 5. `prescription`
- `id` (PK, BIGINT)
- `doctor_id` (FK to doctor.id)
- `patient_id` (FK to patient.id)
- `appointment_id` (FK to appointment.id)
- `diagnosis` (VARCHAR)
- `medication` (VARCHAR)
- `prescription_date` (DATE)

### Relationships
- Doctor `1 : N` Appointment
- Patient `1 : N` Appointment
- Appointment `1 : 1` Prescription
