package cpsc.au.cpsc5130.hospitalmanagementapplication;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.*;
import java.util.Optional;

public class DiagnosesController {

    @FXML
    private TableView<Diagnosis> diagnosisTable;
    @FXML
    private TableColumn<Diagnosis, Integer> colDiagnosisId;
    @FXML
    private TableColumn<Diagnosis, String> colDiagnosisName;

    @FXML
    private TextField txtDiagnosisName;
    @FXML
    private TextArea txtDescription;

    private ObservableList<Diagnosis> diagnoses = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        colDiagnosisId.setCellValueFactory(cd ->
                new javafx.beans.property.SimpleIntegerProperty(cd.getValue().getDiagnosisId()).asObject()
        );
        colDiagnosisName.setCellValueFactory(cd ->
                new javafx.beans.property.SimpleStringProperty(cd.getValue().getDiagnosisName())
        );

        loadDiagnoses();
        diagnosisTable.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                txtDiagnosisName.setText(newVal.getDiagnosisName());
                txtDescription.setText(newVal.getDescription());
            } else {
                txtDiagnosisName.clear();
                txtDescription.clear();
            }
        });
    }

    private void loadDiagnoses() {
        diagnoses.clear();
        String sql = "SELECT diagnosis_id, diagnosis_name, description FROM diagnoses";
        try {
            Connection conn = DatabaseManager.getConnection();
            try (PreparedStatement stmt = conn.prepareStatement(sql);
                 ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {
                    int id = rs.getInt("diagnosis_id");
                    String name = rs.getString("diagnosis_name");
                    String desc = rs.getString("description");
                    diagnoses.add(new Diagnosis(id, name, desc));
                }
            }
            diagnosisTable.setItems(diagnoses);
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error loading diagnoses: " + e.getMessage());
        }
    }

    @FXML
    private void handleAdd() {
        String name = txtDiagnosisName.getText();
        String desc = txtDescription.getText();
        if (desc.length() > 500) {
            desc = desc.substring(0, 500);
        }

        String insertSQL = "INSERT INTO diagnoses (diagnosis_name, description) VALUES (?, ?)";
        try {
            Connection conn = DatabaseManager.getConnection();
            try (PreparedStatement stmt = conn.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS)) {
                stmt.setString(1, name);
                stmt.setString(2, desc);
                stmt.executeUpdate();
            }
            // Refresh the diagnoses view after insert
            loadDiagnoses();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error adding diagnosis: " + e.getMessage());
        }
    }

    @FXML
    private void handleUpdate() {
        Diagnosis sel = diagnosisTable.getSelectionModel().getSelectedItem();
        if (sel == null) {
            return;
        }
        int diagId = sel.getDiagnosisId();

        String name = txtDiagnosisName.getText();
        String desc = txtDescription.getText();
        if (desc.length() > 500) {
            desc = desc.substring(0, 500);
        }

        String updSQL = "UPDATE diagnoses SET diagnosis_name=?, description=? WHERE diagnosis_id=?";
        try {
            Connection conn = DatabaseManager.getConnection();
            try (PreparedStatement stmt = conn.prepareStatement(updSQL)) {
                stmt.setString(1, name);
                stmt.setString(2, desc);
                stmt.setInt(3, diagId);
                stmt.executeUpdate();
            }
            // Refresh the diagnoses view after update
            loadDiagnoses();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleDelete() {
        Diagnosis sel = diagnosisTable.getSelectionModel().getSelectedItem();
        if (sel == null) return;

        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.setTitle("Delete?");
        confirm.setHeaderText("Delete diagnosis " + sel.getDiagnosisId() + "?");
        confirm.setContentText("Are you sure?");
        Optional<ButtonType> r = confirm.showAndWait();
        if (r.isEmpty() || r.get() != ButtonType.OK) {
            return;
        }

        String delSQL = "DELETE FROM diagnoses WHERE diagnosis_id=?";
        try {
            Connection conn = DatabaseManager.getConnection();
            try (PreparedStatement stmt = conn.prepareStatement(delSQL)) {
                stmt.setInt(1, sel.getDiagnosisId());
                stmt.executeUpdate();
            }
            // Refresh view and clear form after deletion
            loadDiagnoses();
            txtDiagnosisName.clear();
            txtDescription.clear();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}