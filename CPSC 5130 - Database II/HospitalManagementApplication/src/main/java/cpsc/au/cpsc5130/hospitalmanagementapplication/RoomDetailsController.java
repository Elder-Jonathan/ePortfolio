package cpsc.au.cpsc5130.hospitalmanagementapplication;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ChoiceDialog;
import javafx.stage.Stage;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RoomDetailsController {

    @FXML private Label roomNumberLabel;
    @FXML private Label floorLabel;
    @FXML private Label occupiedLabel;
    @FXML private Label assignedPatientLabel;
    @FXML private Label assignedDoctorLabel;
    @FXML private Label admissionDateLabel;

    /**
     * Load the room details from DB, storing them in the labels.
     */
    public void loadRoom(int roomNumber) {
        try {
            Connection conn = DatabaseManager.getConnection();
            String query = "SELECT * FROM rooms WHERE room_number = ?";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, roomNumber);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        roomNumberLabel.setText(String.valueOf(rs.getInt("room_number")));
                        floorLabel.setText(String.valueOf(rs.getInt("floor")));
                        occupiedLabel.setText(rs.getBoolean("occupied") ? "Yes" : "No");

                        int occupantId = rs.getInt("assigned_patient");
                        if (rs.wasNull()) {
                            assignedPatientLabel.setText("Not Available");
                        } else {
                            assignedPatientLabel.setText(String.valueOf(occupantId));
                        }

                        String doctor = rs.getString("assigned_doctor");
                        assignedDoctorLabel.setText((doctor == null) ? "Not Available" : doctor);

                        Date admissionDate = rs.getDate("admission_date");
                        if (admissionDate != null) {
                            admissionDateLabel.setText(admissionDate.toString());
                        } else {
                            admissionDateLabel.setText("Not Available");
                        }
                    } else {
                        roomNumberLabel.setText(String.valueOf(roomNumber));
                        floorLabel.setText("Not Available");
                        occupiedLabel.setText("Not Available");
                        assignedPatientLabel.setText("Not Available");
                        assignedDoctorLabel.setText("Not Available");
                        admissionDateLabel.setText("Not Available");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error loading room details: " + e.getMessage());
        }
    }

    @FXML
    private void handleBack() {
        Stage stage = (Stage) roomNumberLabel.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void handleDischarge() {
        int roomNum;
        try {
            roomNum = Integer.parseInt(roomNumberLabel.getText());
        } catch (NumberFormatException e) {
            System.out.println("Invalid room number label: " + roomNumberLabel.getText());
            return;
        }

        int occupantId;
        try {
            occupantId = Integer.parseInt(assignedPatientLabel.getText());
        } catch (NumberFormatException e) {
            occupantId = -1;
        }

        if (occupantId <= 0) {
            Alert info = new Alert(Alert.AlertType.INFORMATION);
            info.setTitle("No Occupant");
            info.setHeaderText(null);
            info.setContentText("No occupant found in Room #" + roomNum);
            info.showAndWait();
            return;
        }

        try {
            Connection conn = DatabaseManager.getConnection();
            String freeSQL = "UPDATE rooms SET occupied=0, assigned_patient=NULL, assigned_doctor=NULL, admission_date=NULL WHERE room_number=?";
            try (PreparedStatement freeStmt = conn.prepareStatement(freeSQL)) {
                freeStmt.setInt(1, roomNum);
                freeStmt.executeUpdate();
            }

            String findAdmSQL = "SELECT admission_id FROM admissions WHERE patient_id=? AND room_number=? AND discharge_date IS NULL ORDER BY admission_date DESC LIMIT 1";
            int admissionId = -1;
            try (PreparedStatement findAdmStmt = conn.prepareStatement(findAdmSQL)) {
                findAdmStmt.setInt(1, occupantId);
                findAdmStmt.setInt(2, roomNum);
                try (ResultSet rs = findAdmStmt.executeQuery()) {
                    if (rs.next()) {
                        admissionId = rs.getInt("admission_id");
                    }
                }
            }

            if (admissionId == -1) {
                Alert info = new Alert(Alert.AlertType.INFORMATION);
                info.setTitle("No Active Admission");
                info.setHeaderText(null);
                info.setContentText("No active admission found for patient " + occupantId + " in room #" + roomNum);
                info.showAndWait();
            } else {
                String updAdmSQL = "UPDATE admissions SET discharge_date=NOW() WHERE admission_id=?";
                try (PreparedStatement updAdmStmt = conn.prepareStatement(updAdmSQL)) {
                    updAdmStmt.setInt(1, admissionId);
                    updAdmStmt.executeUpdate();
                }

                String chosenTreatment = promptForTreatment(conn);
                if (chosenTreatment != null && !chosenTreatment.isEmpty()) {
                    appendPatientHistory(conn, occupantId, admissionId, chosenTreatment);
                }
            }

            Alert done = new Alert(Alert.AlertType.INFORMATION);
            done.setTitle("Discharged");
            done.setHeaderText(null);
            done.setContentText("Successfully discharged occupant (Patient " + occupantId + ") from room #" + roomNum);
            done.showAndWait();

            Stage stage = (Stage) roomNumberLabel.getScene().getWindow();
            stage.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error discharging occupant from room " + roomNum + ": " + e.getMessage());
        }
    }

    private String promptForTreatment(Connection conn) {
        List<String> treatmentOptions = new ArrayList<>();
        String sql = "SELECT name FROM treatments";
        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                treatmentOptions.add(rs.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error loading treatments: " + e.getMessage());
            return null;
        }

        if (treatmentOptions.isEmpty()) {
            return null;
        }

        ChoiceDialog<String> dialog = new ChoiceDialog<>(treatmentOptions.get(0), treatmentOptions);
        dialog.setTitle("Select Treatment");
        dialog.setHeaderText("Choose a treatment used for this discharge");
        dialog.setContentText("Treatment:");

        Optional<String> result = dialog.showAndWait();
        return result.orElse(null);
    }

    private void appendPatientHistory(Connection conn, int patientId, int admissionId, String treatment) {
        String newRecord = "Discharged from Admission #" + admissionId
                + " on " + new java.sql.Date(System.currentTimeMillis())
                + ". Treatment used: " + treatment;

        String sql = "UPDATE patients SET diagnosis_history = CONCAT(IFNULL(diagnosis_history, ''), '\n', ?) WHERE patient_id=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, newRecord);
            stmt.setInt(2, patientId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error appending patient history: " + e.getMessage());
        }
    }
}
