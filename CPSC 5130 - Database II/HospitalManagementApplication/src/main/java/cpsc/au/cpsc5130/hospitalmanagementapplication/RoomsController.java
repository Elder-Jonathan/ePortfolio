package cpsc.au.cpsc5130.hospitalmanagementapplication;

import javafx.fxml.FXML;
import javafx.scene.control.TabPane;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class RoomsController {

    @FXML
    private TabPane tabPane;

    private Map<Integer, Button> roomButtonMap = new HashMap<>();

    @FXML
    public void initialize() {
        discoverAllRoomButtons();
        refreshRooms();
    }

    private void discoverAllRoomButtons() {
        tabPane.getTabs().forEach(tab -> {
            Node content = tab.getContent();
            if (content instanceof Pane) {
                scanPane((Pane) content);
            }
        });
    }

    private void scanPane(Pane pane) {
        for (Node n : pane.getChildrenUnmodifiable()) {
            if (n instanceof Button) {
                Button b = (Button) n;
                Object ud = b.getUserData();
                if (ud != null) {
                    try {
                        int roomNum = Integer.parseInt(ud.toString());
                        roomButtonMap.put(roomNum, b);
                    } catch (NumberFormatException e) {
                        // ignore
                    }
                }
            } else if (n instanceof Pane) {
                scanPane((Pane) n);
            }
        }
    }

    public void refreshRooms() {
        for (Map.Entry<Integer, Button> entry : roomButtonMap.entrySet()) {
            int roomNum = entry.getKey();
            Button btn  = entry.getValue();
            colorRoomButton(roomNum, btn);
        }
    }

    private void colorRoomButton(int roomNum, Button btn) {
        if (!DatabaseManager.isConnected()) {
            System.out.println("Not connected to DB, cannot color room button.");
            return;
        }

        try {
            Connection conn = DatabaseManager.getConnection();
            try (PreparedStatement stmt = conn.prepareStatement("SELECT occupied FROM rooms WHERE room_number=?")) {
                stmt.setInt(1, roomNum);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        boolean occupied = (rs.getInt("occupied") == 1);
                        if (occupied) {
                            btn.setStyle("-fx-background-color: #E86100; -fx-text-fill: white;");
                        } else {
                            btn.setStyle("-fx-background-color: #0B2341; -fx-text-fill: white;");
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleRoomClick(ActionEvent event) {
        Button clicked = (Button) event.getSource();
        Object ud = clicked.getUserData();
        if (ud == null) return;

        int roomNumber;
        try {
            roomNumber = Integer.parseInt(ud.toString());
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return;
        }

        openRoomDetails(roomNumber);
    }

    private void openRoomDetails(int roomNumber) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("room-detail-view.fxml"));
            Parent root = loader.load();

            RoomDetailsController detailsController = loader.getController();
            detailsController.loadRoom(roomNumber);

            Stage stage = new Stage();
            stage.setTitle("Room " + roomNumber + " Details");
            stage.setScene(new Scene(root, 620, 620));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}