package com.example.finalproject_cpsc5130;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.beans.property.SimpleStringProperty;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

public class HospitalController {

    @FXML
    private TableView<Map<String, Object>> resultTable;

    @FXML
    private TextField patientSearchField;
    @FXML
    private TextField doctorIdField;
    @FXML
    private TextField patientTreatmentIdField;
    @FXML
    private TextField startDateField;
    @FXML
    private TextField endDateField;
    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField patientIdField;

    // (No parameterized constructor—FXML requires a no‑arg constructor.)

    // --- Helper Methods for Dynamic Table Population ---

    // Populates the TableView from a list of maps using the provided column names.
    private void populateTableFromMapList(List<Map<String, Object>> data, List<String> columns) {
        resultTable.getItems().clear();
        resultTable.getColumns().clear();

        // Create table columns dynamically.
        for (String col : columns) {
            TableColumn<Map<String, Object>, String> tableColumn = new TableColumn<>(col);
            tableColumn.setCellValueFactory(cellData -> {
                Object value = cellData.getValue().get(col);
                return new SimpleStringProperty(value != null ? value.toString() : "");
            });
            resultTable.getColumns().add(tableColumn);
        }
        resultTable.setItems(FXCollections.observableArrayList(data));
    }

    // Overloaded method that derives column names from the first row.
    private void populateTableFromMapList(List<Map<String, Object>> data) {
        if (data == null || data.isEmpty()) {
            resultTable.getItems().clear();
            resultTable.getColumns().clear();
            return;
        }
        List<String> columns = new ArrayList<>(data.get(0).keySet());
        populateTableFromMapList(data, columns);
    }

    // Helper method for populating the table from a simple list of strings (e.g., room numbers).
    private void populateTableFromStringList(List<String> data, String columnName) {
        resultTable.getItems().clear();
        resultTable.getColumns().clear();

        TableColumn<Map<String, Object>, String> tableColumn = new TableColumn<>(columnName);
        tableColumn.setCellValueFactory(cellData -> {
            Object value = cellData.getValue().get(columnName);
            return new SimpleStringProperty(value != null ? value.toString() : "");
        });
        resultTable.getColumns().add(tableColumn);

        List<Map<String, Object>> tableData = data.stream().map(item -> {
            Map<String, Object> row = new HashMap<>();
            row.put(columnName, item);
            return row;
        }).collect(Collectors.toList());

        resultTable.setItems(FXCollections.observableArrayList(tableData));
    }

    // --- Section 1: Room Utilization ---

    @FXML
    private void listOccupiedRooms() throws IOException {
        List<String> occupiedRooms = HospitalDatabase.getOccupiedRooms();
        populateTableFromStringList(occupiedRooms, "Room Number");
    }

    @FXML
    private void listUnoccupiedRooms() throws IOException {
        List<String> unoccupiedRooms = HospitalDatabase.getUnoccupiedRooms();
        populateTableFromStringList(unoccupiedRooms, "Room Number");
    }

    @FXML
    private void listAllRooms() throws IOException {
        List<Map<String, Object>> rooms = HospitalDatabase.getAllRooms();
        // The database method now returns maps with keys "Room Number" and "Status"
        populateTableFromMapList(rooms, Arrays.asList("Room Number", "Status"));
    }

    // --- Section 2: Patient Information ---

    @FXML
    private void listAllPatients() {
        try {
            List<Map<String, Object>> patients = HospitalDatabase.getAllPatients();
            populateTableFromMapList(patients, Arrays.asList("PatientID", "FirstName", "LastName", "EmergencyContact", "InsurancePolicy"));
        } catch (IOException | SQLException e) {
            showErrorAlert("Error Listing All Patients", e.getMessage());
        }
    }

    @FXML
    private void listCurrentPatients() {
        try {
            List<Map<String, Object>> currentPatients = HospitalDatabase.getCurrentPatients();
            populateTableFromMapList(currentPatients);
        } catch (IOException | SQLException e) {
            showErrorAlert("Error Listing Current Patients", e.getMessage());
        }
    }

    @FXML
    private void listDischargedPatientsByDate() {
        String startDate = startDateField.getText().trim();
        String endDate = endDateField.getText().trim();
        try {
            List<Map<String, Object>> patients = HospitalDatabase.getDischargedPatients(startDate, endDate);
            populateTableFromMapList(patients);
        } catch (IOException | SQLException e) {
            showErrorAlert("Error Listing Discharged Patients", e.getMessage());
        }
    }

    @FXML
    private void listAdmittedPatientsByDate() {
        String startDate = startDateField.getText().trim();
        String endDate = endDateField.getText().trim();
        try {
            List<Map<String, Object>> patients = HospitalDatabase.getAdmittedPatients(startDate, endDate);
            populateTableFromMapList(patients);
        } catch (IOException | SQLException e) {
            showErrorAlert("Error Listing Admitted Patients", e.getMessage());
        }
    }

    @FXML
    private void searchPatientDetails() {
        String searchTerm = patientSearchField.getText().trim();
        if (!searchTerm.isEmpty()) {
            try {
                List<Map<String, Object>> patientDetails = HospitalDatabase.getSearchPatientDetails(searchTerm);
                populateTableFromMapList(patientDetails);
            } catch (IOException | SQLException e) {
                showErrorAlert("Error Searching Patient Details", e.getMessage());
            }
        }
    }

    // --- Section 2 (continued): Doctor Information ---

    @FXML
    private void listAllDoctors() {
        try {
            List<Map<String, Object>> doctors = HospitalDatabase.getAllDoctors();
            populateTableFromMapList(doctors, Arrays.asList("EmployeeID", "FirstName", "LastName", "JobCategory"));
        } catch (IOException | SQLException e) {
            showErrorAlert("Error Listing All Doctors", e.getMessage());
        }
    }

    @FXML
    private void listDoctorsWithCurrentPatients() {
        try {
            List<Map<String, Object>> doctors = HospitalDatabase.getDoctorsWithCurrentPatients();
            populateTableFromMapList(doctors);
        } catch (IOException | SQLException e) {
            showErrorAlert("Error Listing Doctors with Current Patients", e.getMessage());
        }
    }

    @FXML
    private void doctorSpecificDiagnosisStats() {
        String doctorId = doctorIdField.getText().trim();
        if (!doctorId.isEmpty()) {
            try {
                List<Map<String, Object>> stats = HospitalDatabase.getDoctorSpecificDiagnosisStats(Integer.parseInt(doctorId));
                populateTableFromMapList(stats);
            } catch (IOException | SQLException e) {
                showErrorAlert("Error Getting Doctor Specific Diagnosis Stats", e.getMessage());
            }
        }
    }

    @FXML
    private void doctorSpecificTreatmentStats() {
        String doctorId = doctorIdField.getText().trim();
        if (!doctorId.isEmpty()) {
            try {
                List<Map<String, Object>> stats = HospitalDatabase.getDoctorSpecificTreatmentStats(Integer.parseInt(doctorId));
                populateTableFromMapList(stats);
            } catch (IOException | SQLException e) {
                showErrorAlert("Error Getting Doctor Specific Treatment Stats", e.getMessage());
            }
        }
    }

    // --- Section 3: Diagnosis and Treatment Information ---

    @FXML
    private void listDiagnosesByOccurrences() {
        try {
            List<Map<String, Object>> diagnoses = HospitalDatabase.getDiagnosesByOccurrences();
            populateTableFromMapList(diagnoses);
        } catch (IOException | SQLException e) {
            showErrorAlert("Error Listing Diagnoses by Occurrence", e.getMessage());
        }
    }

    @FXML
    private void treatmentOccurrenceStats() {
        try {
            List<Map<String, Object>> treatments = HospitalDatabase.getTreatmentOccurrenceStats();
            populateTableFromMapList(treatments);
        } catch (IOException | SQLException e) {
            showErrorAlert("Error Listing Treatment Occurrence Stats", e.getMessage());
        }
    }

    @FXML
    private void frequentPatientDiagnosisCorrelation() {
        try {
            List<Map<String, Object>> correlations = HospitalDatabase.getFrequentPatientDiagnosisCorrelation();
            populateTableFromMapList(correlations);
        } catch (IOException | SQLException e) {
            showErrorAlert("Error Listing Frequent Patient Diagnosis Correlation", e.getMessage());
        }
    }

    @FXML
    private void treatmentOrdererAndPatientInfo() {
        String patientTreatmentId = patientTreatmentIdField.getText().trim();
        if (!patientTreatmentId.isEmpty()) {
            try {
                Map<String, Object> result = HospitalDatabase.getTreatmentOrdererAndPatientInfo(Integer.parseInt(patientTreatmentId));
                if (result != null) {
                    populateTableFromMapList(Collections.singletonList(result));
                } else {
                    showInfoAlert("No Data", "No treatment info found for the provided ID.");
                }
            } catch (IOException | SQLException e) {
                showErrorAlert("Error Getting Treatment Orderer and Patient Info", e.getMessage());
            }
        }
    }

    // --- Section 4: Employee Information ---

    @FXML
    private void listAllEmployees() {
        try {
            List<Map<String, Object>> employees = HospitalDatabase.getAllEmployees();
            populateTableFromMapList(employees);
        } catch (IOException | SQLException e) {
            showErrorAlert("Error Listing All Employees", e.getMessage());
        }
    }

    @FXML
    private void doctorsWithHighAdmissionRatePatients() {
        try {
            List<Map<String, Object>> doctors = HospitalDatabase.getDoctorsWithHighAdmissionRatePatients();
            populateTableFromMapList(doctors);
        } catch (IOException | SQLException e) {
            showErrorAlert("Error Listing Doctors with High Admission Rate Patients", e.getMessage());
        }
    }

    @FXML
    private void employeesTreatingAllPatients() {
        try {
            List<Map<String, Object>> employees = HospitalDatabase.getEmployeesTreatingAllPatients();
            populateTableFromMapList(employees);
        } catch (IOException | SQLException e) {
            showErrorAlert("Error Listing Employees Treating All Patients", e.getMessage());
        }
    }

    // --- Utility Methods for Alerts ---

    private void showErrorAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showInfoAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
