package cpsc.au.cpsc5130.hospitalmanagementapplication;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.*;
import java.time.LocalDate;
import java.util.Optional;

public class AdmissionsController {

    @FXML
    private TableView<Admission> admissionTable;
    @FXML
    private TableColumn<Admission, Integer> colAdmissionId;
    @FXML
    private TableColumn<Admission, Integer> colPatientId;
    @FXML
    private TableColumn<Admission, Integer> colRoomNumber;
    @FXML
    private TableColumn<Admission, Date> colAdmissionDate;
    @FXML
    private TableColumn<Admission, Date> colDischargeDate;
    @FXML
    private TableColumn<Admission, Integer> colDoctorId;
    @FXML
    private TableColumn<Admission, String> colDiagnosis;

    @FXML
    private TextField txtPatientId;
    @FXML
    private TextField txtRoomNumber;
    @FXML
    private DatePicker dpAdmissionDate;
    @FXML
    private DatePicker dpDischargeDate;
    @FXML
    private TextField txtPrimaryDoctorId;
    @FXML
    private ComboBox<String> comboDiagnosis;

    private ObservableList<Admission> admissions = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        colAdmissionId.setCellValueFactory(cd ->
                new SimpleIntegerProperty(cd.getValue().getAdmissionId()).asObject()
        );
        colPatientId.setCellValueFactory(cd ->
                new SimpleIntegerProperty(cd.getValue().getPatientId()).asObject()
        );
        colRoomNumber.setCellValueFactory(cd ->
                new SimpleIntegerProperty(cd.getValue().getRoomNumber()).asObject()
        );
        colAdmissionDate.setCellValueFactory(cd ->
                new SimpleObjectProperty<>(cd.getValue().getAdmissionDate())
        );
        colDischargeDate.setCellValueFactory(cd ->
                new SimpleObjectProperty<>(cd.getValue().getDischargeDate())
        );
        colDoctorId.setCellValueFactory(cd ->
                new SimpleIntegerProperty(cd.getValue().getPrimaryDoctorId()).asObject()
        );
        colDiagnosis.setCellValueFactory(cd ->
                new SimpleStringProperty(cd.getValue().getDiagnosis())
        );

        loadAdmissionsFromDB();
        loadDiagnosesForCombo();

        admissionTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
            if (newSel != null) {
                fillForm(newSel);
            } else {
                clearForm();
            }
        });
    }

    private void loadAdmissionsFromDB() {
        admissions.clear();
        String sql = "SELECT admission_id, patient_id, room_number, admission_date, discharge_date, primary_doctor_id, diagnosis FROM admissions";
        try {
            Connection conn = DatabaseManager.getConnection();
            try (PreparedStatement stmt = conn.prepareStatement(sql);
                 ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {
                    int admId = rs.getInt("admission_id");
                    int patId = rs.getInt("patient_id");
                    int rm = rs.getInt("room_number");
                    Date admD = rs.getDate("admission_date");
                    Date disD = rs.getDate("discharge_date");
                    int doc = rs.getInt("primary_doctor_id");
                    String diag = rs.getString("diagnosis");

                    admissions.add(new Admission(admId, patId, rm, admD, disD, doc, diag));
                }
            }
            admissionTable.setItems(admissions);
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error loading admissions: " + e.getMessage());
        }
    }

    private void loadDiagnosesForCombo() {
        ObservableList<String> diagOptions = FXCollections.observableArrayList();
        String sql = "SELECT diagnosis_name FROM diagnoses";
        try {
            Connection conn = DatabaseManager.getConnection();
            try (PreparedStatement stmt = conn.prepareStatement(sql);
                 ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {
                    String name = rs.getString("diagnosis_name");
                    diagOptions.add(name);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error loading diagnoses for combo: " + e.getMessage());
        }
        comboDiagnosis.setItems(diagOptions);
    }

    private void fillForm(Admission a) {
        txtPatientId.setText(String.valueOf(a.getPatientId()));
        txtRoomNumber.setText(String.valueOf(a.getRoomNumber()));

        if (a.getAdmissionDate() != null) {
            dpAdmissionDate.setValue(a.getAdmissionDate().toLocalDate());
        } else {
            dpAdmissionDate.setValue(null);
        }

        if (a.getDischargeDate() != null) {
            dpDischargeDate.setValue(a.getDischargeDate().toLocalDate());
        } else {
            dpDischargeDate.setValue(null);
        }

        txtPrimaryDoctorId.setText(String.valueOf(a.getPrimaryDoctorId()));
        comboDiagnosis.setValue(a.getDiagnosis());
    }

    private void clearForm() {
        txtPatientId.clear();
        txtRoomNumber.clear();
        dpAdmissionDate.setValue(null);
        dpDischargeDate.setValue(null);
        txtPrimaryDoctorId.clear();
        comboDiagnosis.setValue(null);
    }

    @FXML
    private void handleAdd() {
        int patId = Integer.parseInt(txtPatientId.getText());
        int rm = Integer.parseInt(txtRoomNumber.getText());
        java.sql.Date admDate = (dpAdmissionDate.getValue() == null) ? null : Date.valueOf(dpAdmissionDate.getValue());
        java.sql.Date disDate = (dpDischargeDate.getValue() == null) ? null : Date.valueOf(dpDischargeDate.getValue());
        int doc = Integer.parseInt(txtPrimaryDoctorId.getText());
        String diag = comboDiagnosis.getValue();

        String insertSQL = "INSERT INTO admissions (patient_id, room_number, admission_date, discharge_date, primary_doctor_id, diagnosis) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            Connection conn = DatabaseManager.getConnection();
            try (PreparedStatement stmt = conn.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS)) {
                stmt.setInt(1, patId);
                stmt.setInt(2, rm);
                stmt.setDate(3, admDate);
                stmt.setDate(4, disDate);
                stmt.setInt(5, doc);
                stmt.setString(6, diag);
                stmt.executeUpdate();
            }
            // Refresh the admissions table view after an insert
            loadAdmissionsFromDB();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error adding admission: " + e.getMessage());
        }
    }

    @FXML
    private void handleUpdate() {
        Admission sel = admissionTable.getSelectionModel().getSelectedItem();
        if (sel == null) {
            System.out.println("No admission selected.");
            return;
        }
        int admId = sel.getAdmissionId();

        int patId = Integer.parseInt(txtPatientId.getText());
        int rm = Integer.parseInt(txtRoomNumber.getText());
        Date admDate = (dpAdmissionDate.getValue() == null) ? null : Date.valueOf(dpAdmissionDate.getValue());
        Date disDate = (dpDischargeDate.getValue() == null) ? null : Date.valueOf(dpDischargeDate.getValue());
        int doc = Integer.parseInt(txtPrimaryDoctorId.getText());
        String diag = comboDiagnosis.getValue();

        String updateSQL = "UPDATE admissions SET patient_id=?, room_number=?, admission_date=?, discharge_date=?, primary_doctor_id=?, diagnosis=? WHERE admission_id=?";
        try {
            Connection conn = DatabaseManager.getConnection();
            try (PreparedStatement stmt = conn.prepareStatement(updateSQL)) {
                stmt.setInt(1, patId);
                stmt.setInt(2, rm);
                stmt.setDate(3, admDate);
                stmt.setDate(4, disDate);
                stmt.setInt(5, doc);
                stmt.setString(6, diag);
                stmt.setInt(7, admId);
                stmt.executeUpdate();
            }
            // Refresh the admissions table view after an update
            loadAdmissionsFromDB();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error updating admission: " + e.getMessage());
        }
    }

    @FXML
    private void handleDelete() {
        Admission sel = admissionTable.getSelectionModel().getSelectedItem();
        if (sel == null) {
            System.out.println("No admission selected for delete.");
            return;
        }
        int admId = sel.getAdmissionId();

        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.setTitle("Delete Admission?");
        confirm.setHeaderText("Delete admission " + admId + "?");
        confirm.setContentText("Are you sure?");
        Optional<ButtonType> r = confirm.showAndWait();
        if (r.isEmpty() || r.get() != ButtonType.OK) {
            return;
        }

        String delSQL = "DELETE FROM admissions WHERE admission_id=?";
        try {
            Connection conn = DatabaseManager.getConnection();
            try (PreparedStatement stmt = conn.prepareStatement(delSQL)) {
                stmt.setInt(1, admId);
                stmt.executeUpdate();
            }
            // Refresh the admissions table view after a delete
            loadAdmissionsFromDB();
            clearForm();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error deleting admission: " + e.getMessage());
        }
    }

    @FXML
    private void handleDischarge() {
        Admission sel = admissionTable.getSelectionModel().getSelectedItem();
        if (sel == null) return;

        int admId = sel.getAdmissionId();
        String sql = "UPDATE admissions SET discharge_date=NOW() WHERE admission_id=?";
        try {
            Connection conn = DatabaseManager.getConnection();
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, admId);
                stmt.executeUpdate();
            }
            // Refresh the admissions table view after discharging
            loadAdmissionsFromDB();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
