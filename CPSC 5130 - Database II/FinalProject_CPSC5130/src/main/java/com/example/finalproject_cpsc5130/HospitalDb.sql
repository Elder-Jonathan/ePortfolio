-- GET_OCCUPIED_ROOMS
SELECT room_number
FROM rooms
WHERE status = 'occupied';

-- GET_UNOCCUPIED_ROOMS
SELECT room_number
FROM rooms
WHERE status = 'unoccupied';

-- GET_ALL_ROOMS
SELECT
    r.RoomNumber AS `Room Number`,
    r.status AS `Status`
FROM Rooms r
ORDER BY r.RoomNumber;

-- Patient Information

-- 2.1 List all patients in the database, with full personal information.
-- LIST_ALL_PATIENTS
SELECT
    PatientID,
    FirstName,
    LastName,
    EmergencyContact,
    InsurancePolicy
FROM Patients
ORDER BY PatientID;

-- 2.2 List all patients currently admitted to the hospital.
-- LIST_CURRENT_PATIENTS
SELECT
    p.PatientID,
    p.FirstName,
    p.LastName
FROM Patients p
JOIN Admissions a ON p.PatientID = a.PatientID
WHERE a.DischargeDate IS NULL
ORDER BY p.PatientID;

-- 2.3 List all patients who were discharged in a given date range.
-- LIST_DISCHARGED_PATIENTS
SELECT
    p.PatientID,
    p.FirstName,
    p.LastName
FROM Patients p
JOIN Admissions a ON p.PatientID = a.PatientID
WHERE a.DischargeDate BETWEEN '2023-06-01' AND '2023-06-15'
ORDER BY p.PatientID;

-- LIST_ADMITTED_PATIENTS
SELECT
    p.PatientID,
    p.FirstName,
    p.LastName
FROM
    Patients p
JOIN
    Admissions a ON p.PatientID = a.PatientID
WHERE
    a.AdmissionDate BETWEEN '2023-07-15' AND '2023-08-01'
ORDER BY p.PatientID;

-- LIST_ADMISSIONS_BY_PATIENT_NAME
SELECT
    a.PatientID,
    p.FirstName,
    p.LastName,
    a.AdmissionDate,
    a.Diagnosis
FROM
    Admissions a
JOIN
    Patients p ON a.PatientID = p.PatientID
WHERE
    p.FirstName = 'Jonathan' AND p.LastName = 'Elder'
ORDER BY a.AdmissionDate;

-- LIST_TREATMENTS_BY_ADMISSIONS_FOR_PATIENT
SELECT
    a.AdmissionID,
    a.AdmissionDate,
    a.Diagnosis,
    pt.TreatmentDate,
    t.Name AS TreatmentName
FROM
    Admissions a
LEFT JOIN
    PatientTreatments pt ON a.PatientID = pt.PatientID AND a.AdmissionID = pt.PatientID
LEFT JOIN
    Treatments t ON pt.TreatmentID = t.TreatmentID
JOIN
    Patients p ON a.PatientID = p.PatientID
WHERE
    a.PatientID = 0001 OR (p.FirstName = 'Jonathan' AND p.LastName = 'Elder')
ORDER BY a.AdmissionDate DESC, pt.TreatmentDate ASC;

-- LIST_PATIENTS_READMITTED_WITHIN_30_DAYS
SELECT
    a1.PatientID,
    p.FirstName,
    p.LastName,
    a1.Diagnosis,
    e.FirstName AS DoctorFirstName,
    e.LastName AS DoctorLastName
FROM
    Admissions a1
JOIN
    Admissions a2 ON a1.PatientID = a2.PatientID
JOIN
    Patients p ON a1.PatientID = p.PatientID
LEFT JOIN
    Employees e ON a1.PrimaryDoctorID = e.EmployeeID
WHERE
    a1.AdmissionDate BETWEEN a2.DischargeDate AND DATE_ADD(a2.DischargeDate, INTERVAL 30 DAY)
    AND a1.AdmissionID <> a2.AdmissionID
ORDER BY a1.PatientID, a1.AdmissionDate;

-- PATIENT_ADMISSION_STATISTICS
WITH RankedAdmissions AS (
    SELECT
        PatientID,
        AdmissionDate,
        DischargeDate,
        LEAD(AdmissionDate) OVER (PARTITION BY PatientID ORDER BY AdmissionDate) AS NextAdmission
    FROM Admissions
)
SELECT
    PatientID,
    COUNT(*) AS TotalAdmissions,
    AVG(DATEDIFF(DischargeDate, AdmissionDate)) AS AverageDuration,
    MAX(DATEDIFF(NextAdmission, DischargeDate)) AS LongestSpanBetweenAdmissions,
    MIN(DATEDIFF(NextAdmission, DischargeDate)) AS ShortestSpanBetweenAdmissions,
    AVG(DATEDIFF(NextAdmission, DischargeDate)) AS AverageSpanBetweenAdmissions
FROM RankedAdmissions
GROUP BY PatientID
ORDER BY PatientID;

-- Diagnosis and Treatment Information

-- 3.1 List the diagnoses given to patients.
-- LIST_DIAGNOSES_BY_OCCURRENCES
SELECT
    d.DiagnosisID,
    d.DiagnosisName,
    COUNT(a.DiagnosisID) AS TotalOccurrences
FROM Diagnoses d
LEFT JOIN Admissions a ON d.DiagnosisID = a.DiagnosisID
GROUP BY d.DiagnosisID, d.DiagnosisName
ORDER BY TotalOccurrences DESC;

-- TREATMENT_OCCURRENCE_STATS
SELECT
    t.TreatmentID,
    t.Name,
    COUNT(pt.TreatmentID) AS TotalOccurrences
FROM Treatments t
LEFT JOIN PatientTreatments pt ON t.TreatmentID = pt.TreatmentID
GROUP BY t.TreatmentID, t.Name
ORDER BY TotalOccurrences DESC;

-- FREQUENT_PATIENT_DIAGNOSIS_CORRELATION
WITH PatientAdmissions AS (
    SELECT
        PatientID,
        COUNT(DISTINCT AdmissionID) AS AdmissionsCount
    FROM Admissions
    GROUP BY PatientID
    ORDER BY AdmissionsCount DESC
)
SELECT
    d.DiagnosisName,
    COUNT(DISTINCT a.AdmissionID) AS DiagnosisOccurrences
FROM Admissions a
JOIN Diagnoses d ON a.DiagnosisID = d.DiagnosisID
WHERE a.PatientID IN (SELECT PatientID FROM PatientAdmissions)
GROUP BY d.DiagnosisName
ORDER BY DiagnosisOccurrences ASC;

-- TREATMENT_ORDERER_AND_PATIENT_INFO
SELECT
    p.FirstName AS PatientFirstName,
    p.LastName AS PatientLastName,
    e.FirstName AS DoctorFirstName,
    e.LastName AS DoctorLastName
FROM PatientTreatments pt
JOIN Patients p ON pt.PatientID = p.PatientID
JOIN Employees e ON pt.OrderingDoctorID = e.EmployeeID
WHERE pt.PatientTreatmentID = [PatientTreatmentID];

-- Employee Information

-- 4.1 List all workers at the hospital.
-- LIST_ALL_EMPLOYEES
SELECT
    LastName,
    FirstName,
    JobCategory
FROM Employees
ORDER BY LastName ASC, FirstName ASC;

-- DOCTORS_WITH_HIGH_ADMISSION_RATE_PATIENTS
SELECT DISTINCT
    e.EmployeeID,
    e.FirstName AS DoctorFirstName,
    e.LastName AS DoctorLastName
FROM Admissions a
JOIN Employees e ON a.PrimaryDoctorID = e.EmployeeID
WHERE e.JobCategory = 'Doctor'
  AND a.PatientID IN (
      SELECT PatientID
      FROM Admissions
      WHERE AdmissionDate BETWEEN DATE_SUB(CURRENT_DATE, INTERVAL 1 YEAR) AND CURRENT_DATE
      GROUP BY PatientID
      HAVING COUNT(AdmissionID) >= 4
  )
ORDER BY e.LastName, e.FirstName;

-- DOCTOR_SPECIFIC_DIAGNOSIS_STATS
SELECT
    a.DiagnosisID,
    d.DiagnosisName,
    COUNT(a.DiagnosisID) AS Occurrences
FROM Employees e
JOIN Admissions a ON e.EmployeeID = a.PrimaryDoctorID
JOIN Diagnoses d ON a.DiagnosisID = d.DiagnosisID
WHERE e.EmployeeID = [GivenDoctorID]
GROUP BY a.DiagnosisID, d.DiagnosisName
ORDER BY Occurrences DESC;

-- DOCTOR_SPECIFIC_TREATMENT_STATS
SELECT
    t.TreatmentID,
    t.Name AS TreatmentName,
    COUNT(pt.TreatmentID) AS Occurrences
FROM Employees e
JOIN PatientTreatments pt ON e.EmployeeID = pt.OrderingDoctorID
JOIN Treatments t ON pt.TreatmentID = t.TreatmentID
WHERE e.EmployeeID = [GivenDoctorID]
GROUP BY t.TreatmentID, t.Name
ORDER BY Occurrences DESC;

-- EMPLOYEES_TREATING_ALL_PATIENTS
SELECT
    e.EmployeeID, e.FirstName, e.LastName
FROM Employees e
LEFT JOIN (
    SELECT DISTINCT p.PatientID
    FROM Patients p
    JOIN Admissions a ON p.PatientID = a.PatientID
) AS AdmittedPatients
ON NOT EXISTS (
    SELECT 1
    FROM PatientTreatments pt
    WHERE pt.PatientID = AdmittedPatients.PatientID
      AND pt.OrderingDoctorID = e.EmployeeID
)
WHERE AdmittedPatients.PatientID IS NULL
ORDER BY e.LastName, e.FirstName;

-- New Query: SEARCH_PATIENT_DETAILS
-- SEARCH_PATIENT_DETAILS
SELECT
    PatientID,
    FirstName,
    LastName,
    EmergencyContact,
    InsurancePolicy
FROM Patients
WHERE PatientID LIKE '%[SEARCHTERM]%'
   OR FirstName LIKE '%[SEARCHTERM]%'
   OR LastName LIKE '%[SEARCHTERM]%'
ORDER BY PatientID;

-- New Query: LIST_ALL_DOCTORS
-- LIST_ALL_DOCTORS
SELECT
    EmployeeID,
    FirstName,
    LastName,
    JobCategory
FROM Employees
WHERE JobCategory = 'Doctor'
ORDER BY LastName, FirstName;

-- New Query: LIST_DOCTORS_WITH_CURRENT_PATIENTS
-- LIST_DOCTORS_WITH_CURRENT_PATIENTS
SELECT DISTINCT
    e.EmployeeID,
    e.FirstName,
    e.LastName,
    e.JobCategory
FROM Employees e
JOIN Admissions a ON e.EmployeeID = a.PrimaryDoctorID
WHERE a.DischargeDate IS NULL
ORDER BY e.LastName, e.FirstName;
