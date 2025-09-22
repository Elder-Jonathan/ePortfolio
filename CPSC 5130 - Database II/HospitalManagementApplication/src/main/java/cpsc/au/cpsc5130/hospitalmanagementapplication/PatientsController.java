package cpsc.au.cpsc5130.hospitalmanagementapplication;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PatientsController {

    @FXML
    private TableView<Patient> patientTable;
    @FXML
    private TableColumn<Patient, Integer> colPatientId;
    @FXML
    private TableColumn<Patient, String> colFirstName;
    @FXML
    private TableColumn<Patient, String> colLastName;
    // Next columns for your actual DB columns:
    @FXML
    private TableColumn<Patient, String> colEmergencyContact;
    @FXML
    private TableColumn<Patient, String> colInsuranceInfo;
    @FXML
    private TableColumn<Patient, String> colDiagnosisHistory;

    // Form fields
    @FXML
    private TextField txtPatientId;
    @FXML
    private TextField txtFirstName;
    @FXML
    private TextField txtLastName;
    @FXML
    private TextField txtEmergencyContact;
    @FXML
    private TextField txtInsuranceInfo;
    @FXML
    private TextField txtDiagnosisHistory;

    private ObservableList<Patient> patients = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        System.out.println("Initializing Patients View with SQL...");

        colPatientId.setCellValueFactory(cellData ->
                new SimpleIntegerProperty(cellData.getValue().getPatientId()).asObject()
        );
        colFirstName.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getFirstName())
        );
        colLastName.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getLastName())
        );
        colEmergencyContact.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getEmergencyContact())
        );
        colInsuranceInfo.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getInsuranceInfo())
        );
        colDiagnosisHistory.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getDiagnosisHistory())
        );

        loadPatientsFromDB();

        patientTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
            if (newSel != null) {
                txtPatientId.setText(String.valueOf(newSel.getPatientId()));
                txtFirstName.setText(newSel.getFirstName());
                txtLastName.setText(newSel.getLastName());
                txtEmergencyContact.setText(newSel.getEmergencyContact());
                txtInsuranceInfo.setText(newSel.getInsuranceInfo());
                txtDiagnosisHistory.setText(newSel.getDiagnosisHistory());
            } else {
                clearForm();
            }
        });

        patientTable.setOnMouseClicked(event -> {
            if (patientTable.getSelectionModel().getSelectedItem() == null) {
                clearForm();
            }
        });
    }

    private void clearForm() {
        txtPatientId.clear();
        txtFirstName.clear();
        txtLastName.clear();
        txtEmergencyContact.clear();
        txtInsuranceInfo.clear();
        txtDiagnosisHistory.clear();
    }

    private void loadPatientsFromDB() {
        patients.clear();
        String sql = "SELECT patient_id, first_name, last_name, emergency_contact, insurance_info, diagnosis_history FROM patients";
        try {
            Connection conn = DatabaseManager.getConnection();
            try (PreparedStatement stmt = conn.prepareStatement(sql);
                 ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {
                    int id = rs.getInt("patient_id");
                    String fn = rs.getString("first_name");
                    String ln = rs.getString("last_name");
                    String em = rs.getString("emergency_contact");
                    String ins = rs.getString("insurance_info");
                    String dia = rs.getString("diagnosis_history");

                    patients.add(new Patient(id, fn, ln, em, ins, dia));
                }
            }
            patientTable.setItems(patients);
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error loading patients: " + e.getMessage());
        }
    }

    @FXML
    private void handleAddPatient() {
        String fn = txtFirstName.getText().trim();
        String ln = txtLastName.getText().trim();
        if (fn.isEmpty() || ln.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "First and Last Name cannot be empty.");
            alert.showAndWait();
            return;
        }

        if (patientNameExists(fn, ln)) {
            Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
            confirm.setTitle("Duplicate Name");
            confirm.setHeaderText("A patient named '" + fn + " " + ln + "' already exists.");
            confirm.setContentText("Are you sure you want to add another patient with the same name?");
            Optional<ButtonType> result = confirm.showAndWait();
            if (result.isEmpty() || result.get() != ButtonType.OK) {
                return;
            }
        }

        String emerg = txtEmergencyContact.getText().trim();
        String insur = txtInsuranceInfo.getText().trim();
        String diagH = txtDiagnosisHistory.getText().trim();

        String insertSQL = "INSERT INTO patients (first_name, last_name, emergency_contact, insurance_info, diagnosis_history) VALUES (?, ?, ?, ?, ?)";
        try {
            Connection conn = DatabaseManager.getConnection();
            try (PreparedStatement stmt = conn.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS)) {
                stmt.setString(1, fn);
                stmt.setString(2, ln);
                stmt.setString(3, emerg);
                stmt.setString(4, insur);
                stmt.setString(5, diagH);
                stmt.executeUpdate();
            }
            loadPatientsFromDB();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error adding new patient: " + e.getMessage());
        }
    }

    private boolean patientNameExists(String firstName, String lastName) {
        String sql = "SELECT COUNT(*) FROM patients WHERE first_name=? AND last_name=?";
        try {
            Connection conn = DatabaseManager.getConnection();
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, firstName);
                stmt.setString(2, lastName);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        int count = rs.getInt(1);
                        return (count > 0);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @FXML
    private void handleAdmitPatient() {
        Patient selected = patientTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "No patient selected to admit.");
            alert.showAndWait();
            return;
        }

        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.setTitle("Admit Patient");
        confirm.setHeaderText("Admit " + selected.getFirstName() + " " + selected.getLastName() + "?");
        confirm.setContentText("Would you like to assign this patient to a room and doctor now?");
        Optional<ButtonType> result = confirm.showAndWait();

        if (result.isEmpty() || result.get() != ButtonType.OK) {
            return;
        }

        createNewAdmission(selected.getPatientId());
    }

    private void createNewAdmission(int patientId) {
        try {
            Connection conn = DatabaseManager.getConnection();
            int freeRoom = findFirstFreeRoom(conn);
            if (freeRoom == -1) {
                Alert none = new Alert(Alert.AlertType.INFORMATION, "No free rooms available.");
                none.showAndWait();
                return;
            }

            int chosenDocId = promptForDoctor(conn);
            if (chosenDocId == -1) {
                Alert info = new Alert(Alert.AlertType.INFORMATION, "No doctor chosen. Admission canceled.");
                info.showAndWait();
                return;
            }

            String insertSQL = "INSERT INTO admissions (patient_id, room_number, admission_date, primary_doctor_id) VALUES (?, ?, NOW(), ?)";
            try (PreparedStatement stmt = conn.prepareStatement(insertSQL)) {
                stmt.setInt(1, patientId);
                stmt.setInt(2, freeRoom);
                stmt.setInt(3, chosenDocId);
                stmt.executeUpdate();
            }

            String updateRoom = "UPDATE rooms SET occupied=1, assigned_patient=?, assigned_doctor=?, admission_date=NOW() WHERE room_number=?";
            try (PreparedStatement stmt2 = conn.prepareStatement(updateRoom)) {
                stmt2.setInt(1, patientId);
                stmt2.setInt(2, chosenDocId);
                stmt2.setInt(3, freeRoom);
                stmt2.executeUpdate();
            }

            Alert done = new Alert(Alert.AlertType.INFORMATION, "Patient admitted to Room #" + freeRoom + " with Doctor ID #" + chosenDocId);
            done.showAndWait();

            loadPatientsFromDB();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private int findFirstFreeRoom(Connection conn) throws SQLException {
        String sql = "SELECT room_number FROM rooms WHERE occupied=0 ORDER BY room_number LIMIT 1";
        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                return rs.getInt("room_number");
            }
        }
        return -1;
    }

    @FXML
    private void handleAdd() {
        String fn = txtFirstName.getText();
        String ln = txtLastName.getText();
        String em = txtEmergencyContact.getText();
        String ins = txtInsuranceInfo.getText();
        String dia = txtDiagnosisHistory.getText();

        String insertSQL = "INSERT INTO patients (first_name, last_name, emergency_contact, insurance_info, diagnosis_history) VALUES (?, ?, ?, ?, ?)";
        try {
            Connection conn = DatabaseManager.getConnection();
            try (PreparedStatement stmt = conn.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS)) {
                stmt.setString(1, fn);
                stmt.setString(2, ln);
                stmt.setString(3, em);
                stmt.setString(4, ins);
                stmt.setString(5, dia);
                stmt.executeUpdate();
            }
            loadPatientsFromDB();

            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Assign Room?");
            alert.setHeaderText("Patient " + fn + " " + ln + " added.");
            alert.setContentText("Would you like to assign this patient to a room now?");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {
                Patient newP = patients.stream().filter(p -> p.getFirstName().equals(fn) && p.getLastName().equals(ln)).findFirst().orElse(null);
                if (newP != null) {
                    assignPatientToAvailableRoom(newP);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error adding new patient: " + e.getMessage());
        }
    }

    private void assignPatientToAvailableRoom(Patient p) {
        try {
            Connection conn = DatabaseManager.getConnection();
            String findRoomSQL = "SELECT room_number FROM rooms WHERE occupied=0 ORDER BY room_number LIMIT 1";
            int roomNumber = -1;
            try (PreparedStatement findStmt = conn.prepareStatement(findRoomSQL);
                 ResultSet rs = findStmt.executeQuery()) {
                if (!rs.next()) {
                    Alert noRooms = new Alert(Alert.AlertType.INFORMATION);
                    noRooms.setTitle("No Rooms Available");
                    noRooms.setHeaderText(null);
                    noRooms.setContentText("There are no unoccupied rooms at this time.");
                    noRooms.showAndWait();
                    return;
                }
                roomNumber = rs.getInt("room_number");
            }

            String doctorString = promptForDoctorString(conn);
            if (doctorString == null) {
                doctorString = "N/A";
            }

            String updateRoomSQL = "UPDATE rooms SET occupied=1, assigned_patient=?, assigned_doctor=?, admission_date=NOW() WHERE room_number=?";
            try (PreparedStatement stmt = conn.prepareStatement(updateRoomSQL)) {
                stmt.setInt(1, p.getPatientId());
                stmt.setString(2, doctorString);
                stmt.setInt(3, roomNumber);
                stmt.executeUpdate();
            }

            String insertAdmSQL = "INSERT INTO admissions (patient_id, room_number, admission_date, primary_doctor_id, diagnosis) VALUES (?, ?, NOW(), NULL, NULL)";
            try (PreparedStatement stmt = conn.prepareStatement(insertAdmSQL)) {
                stmt.setInt(1, p.getPatientId());
                stmt.setInt(2, roomNumber);
                stmt.executeUpdate();
            }

            Alert done = new Alert(Alert.AlertType.INFORMATION);
            done.setTitle("Room Assigned");
            done.setHeaderText(null);
            done.setContentText("Assigned patient ID " + p.getPatientId()
                    + " to room #" + roomNumber
                    + " with assigned_doctor='" + doctorString + "'."
                    + "\nAdmission record also created.");
            done.showAndWait();

            loadPatientsFromDB();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error assigning patient to room: " + e.getMessage());
        }
    }

    private String promptForDoctorString(Connection conn) {
        String sql = "SELECT first_name, last_name, department FROM employees WHERE job_category='Doctor'";
        ArrayList<String> doctorOptions = new ArrayList<>();

        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                String fn = rs.getString("first_name");
                String ln = rs.getString("last_name");
                String dept = rs.getString("department");
                String display = fn + " " + ln + " - " + dept;
                doctorOptions.add(display);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error loading doctors: " + e.getMessage());
            return null;
        }

        if (doctorOptions.isEmpty()) {
            Alert info = new Alert(Alert.AlertType.INFORMATION);
            info.setTitle("No Doctors");
            info.setHeaderText(null);
            info.setContentText("No employees with job_category='Doctor'.");
            info.showAndWait();
            return null;
        }

        ChoiceDialog<String> dialog = new ChoiceDialog<>(doctorOptions.get(0), doctorOptions);
        dialog.setTitle("Pick a Doctor");
        dialog.setHeaderText("Select a doctor for this room");
        dialog.setContentText("Doctor:");

        Optional<String> result = dialog.showAndWait();
        return result.orElse(null);
    }

    private int promptForDoctor(Connection conn) {
        List<String> displayList = new ArrayList<>();
        List<Integer> idList = new ArrayList<>();

        String sql = "SELECT employee_id, first_name, last_name, department FROM employees WHERE job_category='Doctor'";
        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int docId = rs.getInt("employee_id");
                String fn = rs.getString("first_name");
                String ln = rs.getString("last_name");
                String dept = rs.getString("department");
                String display = fn + " " + ln + " (ID=" + docId + ") - " + dept;
                displayList.add(display);
                idList.add(docId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }

        if (displayList.isEmpty()) {
            return -1;
        }

        ChoiceDialog<String> dialog = new ChoiceDialog<>(displayList.get(0), displayList);
        dialog.setTitle("Select a Doctor");
        dialog.setHeaderText("Choose a Primary Doctor for this admission");
        dialog.setContentText("Doctor:");

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            String chosen = result.get();
            int idx = displayList.indexOf(chosen);
            if (idx >= 0) {
                return idList.get(idx);
            }
        }
        return -1;
    }

    @FXML
    private void handleUpdate() {
        Patient selected = patientTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "No patient selected for update.");
            alert.showAndWait();
            return;
        }

        int pid = selected.getPatientId();
        String fn = txtFirstName.getText().trim();
        String ln = txtLastName.getText().trim();
        String emerg = txtEmergencyContact.getText().trim();
        String insur = txtInsuranceInfo.getText().trim();
        String diagH = txtDiagnosisHistory.getText().trim();

        String updateSQL = "UPDATE patients SET first_name=?, last_name=?, emergency_contact=?, insurance_info=?, diagnosis_history=? WHERE patient_id=?";
        try {
            Connection conn = DatabaseManager.getConnection();
            try (PreparedStatement stmt = conn.prepareStatement(updateSQL)) {
                stmt.setString(1, fn);
                stmt.setString(2, ln);
                stmt.setString(3, emerg);
                stmt.setString(4, insur);
                stmt.setString(5, diagH);
                stmt.setInt(6, pid);
                stmt.executeUpdate();
            }
            loadPatientsFromDB();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error updating patient: " + e.getMessage());
        }
    }

    @FXML
    private void handleDelete() {
        Patient sel = patientTable.getSelectionModel().getSelectedItem();
        if (sel == null) {
            System.out.println("No patient selected for delete.");
            return;
        }
        int patId = sel.getPatientId();

        String deleteSQL = "DELETE FROM patients WHERE patient_id=?";
        try {
            Connection conn = DatabaseManager.getConnection();
            try (PreparedStatement stmt = conn.prepareStatement(deleteSQL)) {
                stmt.setInt(1, patId);
                stmt.executeUpdate();
            }
            loadPatientsFromDB();
            clearForm();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error deleting patient: " + e.getMessage());
        }
    }

    @FXML
    private void handleDischarge() {
        Patient sel = patientTable.getSelectionModel().getSelectedItem();
        if (sel == null) {
            System.out.println("No patient selected for discharge.");
            return;
        }
        int occupantID = sel.getPatientId();

        try {
            Connection conn = DatabaseManager.getConnection();
            String findSQL = "SELECT room_number FROM rooms WHERE assigned_patient=? LIMIT 1";
            int roomNumber = -1;
            try (PreparedStatement findStmt = conn.prepareStatement(findSQL)) {
                findStmt.setInt(1, occupantID);
                try (ResultSet rs = findStmt.executeQuery()) {
                    if (!rs.next()) {
                        Alert info = new Alert(Alert.AlertType.INFORMATION);
                        info.setTitle("Not Found");
                        info.setHeaderText(null);
                        info.setContentText("No room is currently occupied by patient ID " + occupantID);
                        info.showAndWait();
                        return;
                    }
                    roomNumber = rs.getInt("room_number");
                }
            }

            String updateSQL = "UPDATE rooms SET occupied=0, assigned_patient=NULL, assigned_doctor=NULL, admission_date=NULL WHERE room_number=?";
            try (PreparedStatement updateStmt = conn.prepareStatement(updateSQL)) {
                updateStmt.setInt(1, roomNumber);
                updateStmt.executeUpdate();
            }

            Alert done = new Alert(Alert.AlertType.INFORMATION);
            done.setTitle("Discharged");
            done.setHeaderText(null);
            done.setContentText("Patient ID " + occupantID + " is discharged from Room #" + roomNumber);
            done.showAndWait();

            loadPatientsFromDB();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error discharging patient: " + e.getMessage());
        }
    }
}
