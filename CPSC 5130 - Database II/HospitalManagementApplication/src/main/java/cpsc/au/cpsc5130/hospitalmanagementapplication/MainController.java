package cpsc.au.cpsc5130.hospitalmanagementapplication;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;

public class MainController {

    @FXML
    private AnchorPane contentPane;  // This AnchorPane (in MainLayout.fxml) will hold the dynamic view.

    @FXML
    private TextField txtDBName;
    @FXML
    private TextField txtDBUser;
    @FXML
    private TextField txtDBPass;
    @FXML
    private Label lblStatus;
    @FXML
    private Button btnLogout;

    @FXML
    public void initialize() {
        // Load the default view – the rooms view – when the application starts.
        loadView("rooms-view.fxml");

        lblStatus.setText("Not connected");
        btnLogout.setDisable(true);

        // Optionally set placeholders:
        txtDBName.setPromptText("Database Name");
        txtDBUser.setPromptText("Database User");
        txtDBPass.setPromptText("Database Password");
    }

    @FXML
    private void handleRoomLayout() {
        loadView("rooms-view.fxml");
    }

    @FXML
    private void handleEmployees() {
        loadView("employees-view.fxml");
    }

    @FXML
    private void handlePatients() {
        loadView("patients-view.fxml");
    }

    @FXML
    private void handleAdmissions() {
        loadView("admissions-view.fxml");
    }

    @FXML
    private void handleDiagnoses() {
        loadView("diagnoses-view.fxml");
    }

    @FXML
    private void handleTreatments() {
        loadView("treatments-view.fxml");
    }

    private void loadView(String fxmlFile) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent view = loader.load();
            contentPane.getChildren().setAll(view);
            AnchorPane.setTopAnchor(view, 0.0);
            AnchorPane.setBottomAnchor(view, 0.0);
            AnchorPane.setLeftAnchor(view, 0.0);
            AnchorPane.setRightAnchor(view, 0.0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleLogin() {
        String dbName = txtDBName.getText().trim();
        String user = txtDBUser.getText().trim();
        String pass = txtDBPass.getText().trim();

        boolean success = DatabaseManager.connect(dbName, user, pass);
        if (success) {
            lblStatus.setText("Connected as " + user);
            txtDBName.setDisable(true);
            txtDBUser.setDisable(true);
            txtDBPass.setDisable(true);
            btnLogout.setDisable(false);
        } else {
            lblStatus.setText("Login failed. Check credentials and ensure you're on VPN.");
        }
    }

    @FXML
    private void handleLogout() {
        DatabaseManager.closeConnection();
        lblStatus.setText("Not connected");
        txtDBName.setDisable(false);
        txtDBUser.setDisable(false);
        txtDBPass.setDisable(false);
        btnLogout.setDisable(true);
    }
}
