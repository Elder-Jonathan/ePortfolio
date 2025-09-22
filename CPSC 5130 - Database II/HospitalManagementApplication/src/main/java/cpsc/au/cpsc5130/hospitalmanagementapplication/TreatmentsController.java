package cpsc.au.cpsc5130.hospitalmanagementapplication;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.*;
import java.util.Optional;

public class TreatmentsController {
    @FXML
    private TableView<Treatment> treatmentTable;
    @FXML
    private TableColumn<Treatment, Integer> colTreatmentId;
    @FXML
    private TableColumn<Treatment, String> colName;

    @FXML
    private TextField txtName;
    @FXML
    private TextArea txtDescription;

    private ObservableList<Treatment> treatments = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        colTreatmentId.setCellValueFactory(cd ->
                new javafx.beans.property.SimpleIntegerProperty(cd.getValue().getTreatmentId()).asObject()
        );
        colName.setCellValueFactory(cd ->
                new javafx.beans.property.SimpleStringProperty(cd.getValue().getName())
        );

        loadTreatments();
        treatmentTable.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                txtName.setText(newVal.getName());
                txtDescription.setText(newVal.getDescription());
            } else {
                txtName.clear();
                txtDescription.clear();
            }
        });
    }

    private void loadTreatments() {
        treatments.clear();
        String sql = "SELECT treatment_id, name, description FROM treatments";
        try {
            Connection conn = DatabaseManager.getConnection();
            try (PreparedStatement stmt = conn.prepareStatement(sql);
                 ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int tid = rs.getInt("treatment_id");
                    String nm = rs.getString("name");
                    String desc = rs.getString("description");
                    treatments.add(new Treatment(tid, nm, desc));
                }
            }
            treatmentTable.setItems(treatments);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleAdd() {
        String nm = txtName.getText();
        String desc = txtDescription.getText();
        if (desc.length() > 500) desc = desc.substring(0, 500);

        String insertSQL = "INSERT INTO treatments (name, description) VALUES (?, ?)";
        try {
            Connection conn = DatabaseManager.getConnection();
            try (PreparedStatement stmt = conn.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS)) {
                stmt.setString(1, nm);
                stmt.setString(2, desc);
                stmt.executeUpdate();
            }
            // Refresh the treatments view after adding a new treatment
            loadTreatments();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleUpdate() {
        Treatment sel = treatmentTable.getSelectionModel().getSelectedItem();
        if (sel == null) return;

        int tid = sel.getTreatmentId();
        String nm = txtName.getText();
        String desc = txtDescription.getText();
        if (desc.length() > 500) desc = desc.substring(0, 500);

        String updateSQL = "UPDATE treatments SET name=?, description=? WHERE treatment_id=?";
        try {
            Connection conn = DatabaseManager.getConnection();
            try (PreparedStatement stmt = conn.prepareStatement(updateSQL)) {
                stmt.setString(1, nm);
                stmt.setString(2, desc);
                stmt.setInt(3, tid);
                stmt.executeUpdate();
            }
            // Refresh the treatments view after update
            loadTreatments();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleDelete() {
        Treatment sel = treatmentTable.getSelectionModel().getSelectedItem();
        if (sel == null) return;

        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.setTitle("Delete?");
        confirm.setHeaderText("Delete treatment ID " + sel.getTreatmentId() + "?");
        confirm.setContentText("Are you sure?");
        Optional<ButtonType> r = confirm.showAndWait();
        if (r.isEmpty() || r.get() != ButtonType.OK) {
            return;
        }

        String delSQL = "DELETE FROM treatments WHERE treatment_id=?";
        try {
            Connection conn = DatabaseManager.getConnection();
            try (PreparedStatement stmt = conn.prepareStatement(delSQL)) {
                stmt.setInt(1, sel.getTreatmentId());
                stmt.executeUpdate();
            }
            // Refresh the treatments view after deletion
            loadTreatments();
            txtName.clear();
            txtDescription.clear();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
