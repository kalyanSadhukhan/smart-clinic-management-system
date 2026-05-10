DROP PROCEDURE IF EXISTS GetDailyAppointmentReportByDoctor;
DROP PROCEDURE IF EXISTS GetDoctorWithMostPatientsByMonth;
DROP PROCEDURE IF EXISTS GetDoctorWithMostPatientsByYear;

CREATE PROCEDURE GetDailyAppointmentReportByDoctor(IN doc_id BIGINT, IN appt_date DATE)
    SELECT a.id, a.appointment_time, p.name as patient_name, a.status 
    FROM appointment a 
    JOIN patient p ON a.patient_id = p.id 
    WHERE a.doctor_id = doc_id AND DATE(a.appointment_time) = appt_date;

CREATE PROCEDURE GetDoctorWithMostPatientsByMonth(IN target_month INT, IN target_year INT)
    SELECT d.id, d.name, COUNT(DISTINCT a.patient_id) as patient_count 
    FROM doctor d
    JOIN appointment a ON d.id = a.doctor_id
    WHERE MONTH(a.appointment_time) = target_month AND YEAR(a.appointment_time) = target_year
    GROUP BY d.id, d.name
    ORDER BY patient_count DESC
    LIMIT 1;

CREATE PROCEDURE GetDoctorWithMostPatientsByYear(IN target_year INT)
    SELECT d.id, d.name, COUNT(DISTINCT a.patient_id) as patient_count 
    FROM doctor d
    JOIN appointment a ON d.id = a.doctor_id
    WHERE YEAR(a.appointment_time) = target_year
    GROUP BY d.id, d.name
    ORDER BY patient_count DESC
    LIMIT 1;
