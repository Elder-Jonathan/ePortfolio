package edu.au.cpsc.module4;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.Serializable;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.HashSet;

public class FlightScheduleController implements Serializable {

    private final AirlineDatabase database = AirlineDatabaseIO.getDatabase();

    // NEW: Track whether there are unsaved changes.
    private boolean unsavedChanges = false;

    @FXML
    private TableView<ScheduledFlight> flightTable;

    @FXML
    private TableColumn<ScheduledFlight, String> flightDesignatorColumn;

    @FXML
    private TableColumn<ScheduledFlight, String> departureAirportColumn;

    @FXML
    private TableColumn<ScheduledFlight, String> arrivalAirportColumn;

    // UPDATED: Bind to the formatted days-of-week string.
    @FXML
    private TableColumn<ScheduledFlight, String> dayOfWeekColumn;

    @FXML
    private TextField flightDesignatorField;

    @FXML
    private TextField departureAirportField;

    // NEW: TextField for departure time.
    @FXML
    private TextField departureTimeField;

    @FXML
    private TextField arrivalAirportField;

    @FXML
    private TextField arrivalTimeField;

    @FXML
    private ToggleButton mondayButton;

    @FXML
    private ToggleButton tuesdayButton;

    @FXML
    private ToggleButton wednesdayButton;

    @FXML
    private ToggleButton thursdayButton;

    @FXML
    private ToggleButton fridayButton;

    @FXML
    private ToggleButton saturdayButton;

    @FXML
    private ToggleButton sundayButton;

    public void initialize() {
        flightDesignatorColumn.setCellValueFactory(new PropertyValueFactory<>("flightDesignator"));
        departureAirportColumn.setCellValueFactory(new PropertyValueFactory<>("departureAirportIdent"));
        arrivalAirportColumn.setCellValueFactory(new PropertyValueFactory<>("arrivalAirportIdent"));
        // Use the helper method from ScheduledFlight to display days as a compact string.
        dayOfWeekColumn.setCellValueFactory(new PropertyValueFactory<>("daysOfWeekString"));

        flightTable.getSelectionModel().selectedItemProperty().addListener((obs, oldFlight, newFlight) -> {
            if (newFlight != null) {
                populateDetailEditor(newFlight);
            }
        });
    }

    private void populateDetailEditor(ScheduledFlight flight) {
        flightDesignatorField.setText(flight.getFlightDesignator());
        departureAirportField.setText(flight.getDepartureAirportIdent());
        // NEW: Populate the departure time field.
        departureTimeField.setText(flight.getDepartureTime() != null ? flight.getDepartureTime().toString() : "");
        arrivalAirportField.setText(flight.getArrivalAirportIdent());
        arrivalTimeField.setText(flight.getArrivalTime() != null ? flight.getArrivalTime().toString() : "");
        mondayButton.setSelected(flight.getDayOfWeek().contains("M"));
        tuesdayButton.setSelected(flight.getDayOfWeek().contains("T"));
        wednesdayButton.setSelected(flight.getDayOfWeek().contains("W"));
        thursdayButton.setSelected(flight.getDayOfWeek().contains("R"));
        fridayButton.setSelected(flight.getDayOfWeek().contains("F"));
        saturdayButton.setSelected(flight.getDayOfWeek().contains("S"));
        sundayButton.setSelected(flight.getDayOfWeek().contains("U"));
    }

    @FXML
    public void addFlightAction() {
        String flightDesignator = flightDesignatorField.getText();
        String departureIdent = departureAirportField.getText();
        String arrivalIdent = arrivalAirportField.getText();

        if (isDuplicate(flightDesignator, arrivalIdent, departureIdent)) {
            showAlert("Duplicate Entry", "This flight already exists! Please update the existing entry.", Alert.AlertType.ERROR);
            return;
        }

        try {
            ScheduledFlight flight = new ScheduledFlight();
            flight.setFlightDesignator(flightDesignator);
            flight.setDepartureAirportIdent(departureIdent);
            flight.setArrivalAirportIdent(arrivalIdent);

            // NEW: Set departure time from the new text field.
            try {
                flight.setDepartureTime(LocalTime.parse(departureTimeField.getText()));
            } catch (DateTimeParseException e) {
                throw new IllegalArgumentException("Invalid departure time format. Please use the format HH:MM with leading zeros if necessary.");
            }

            try {
                flight.setArrivalTime(LocalTime.parse(arrivalTimeField.getText()));
            } catch (DateTimeParseException e) {
                throw new IllegalArgumentException("Invalid arrival time format. Please use the format HH:MM with leading zeros if necessary.");
            }

            flight.setDayOfWeek(getSelectedDays());

            // Add the flight to the database.
            database.addScheduledFlight(flight);
            // Also add it to the table view.
            flightTable.getItems().add(flight);

            unsavedChanges = true;

            // Clear the detail editor fields.
            clearDetailEditor();
        } catch (IllegalArgumentException ex) {
            showAlert("Error", ex.getMessage(), Alert.AlertType.ERROR);
        }
    }

    private HashSet<String> getSelectedDays() {
        HashSet<String> days = new HashSet<>();
        if (mondayButton.isSelected()) days.add("M");
        if (tuesdayButton.isSelected()) days.add("T");
        if (wednesdayButton.isSelected()) days.add("W");
        if (thursdayButton.isSelected()) days.add("R");
        if (fridayButton.isSelected()) days.add("F");
        if (saturdayButton.isSelected()) days.add("S");
        if (sundayButton.isSelected()) days.add("U");
        return days;
    }

    @FXML
    public void SaveButtonAction(ActionEvent event) {
        database.setFlights(new java.util.HashSet<>(flightTable.getItems()));
        AirlineDatabaseIO.saveDatabase();
        unsavedChanges = false;
        new Alert(AlertType.INFORMATION, "Flight successfully saved to the database.").showAndWait();
    }

    @FXML
    public void RemoveButtonAction(ActionEvent event) {
        ScheduledFlight selectedFlight = flightTable.getSelectionModel().getSelectedItem();
        if (selectedFlight != null) {
            flightTable.getItems().remove(selectedFlight);
            database.removeScheduledFlight(selectedFlight);
            // Removal is immediately saved.
            AirlineDatabaseIO.saveDatabase();
            unsavedChanges = false;
            new Alert(AlertType.INFORMATION, "Flight removed successfully from table view and database.").showAndWait();
        } else {
            new Alert(AlertType.WARNING, "No flight selected to remove. Remember removing also removes from the database.").showAndWait();
        }
        clearDetailEditor();
    }

    @FXML
    public void LoadButtonAction(ActionEvent event) {
        if (unsavedChanges) {
            showAlert("Unsaved Changes", "You have unsaved changes. Please save your changes before loading a new database.", Alert.AlertType.WARNING);
            return;
        }
        clearDetailEditor();
        try {
            AirlineDatabase loadedDb = AirlineDatabaseIO.getDatabase(); // Loads from flights.dat
            flightTable.getItems().clear();
            flightTable.getItems().addAll(loadedDb.getFlights());
            showAlert("Success", "Database loaded successfully. Remember to save any new changes.", Alert.AlertType.INFORMATION);
        } catch(Exception ex) {
            ex.printStackTrace();
            showAlert("Error", "Error loading the database. " + ex.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    public void UpdateButtonAction(ActionEvent event) {
        ScheduledFlight selectedFlight = flightTable.getSelectionModel().getSelectedItem();
        if (selectedFlight != null) {
            try {
                selectedFlight.setFlightDesignator(flightDesignatorField.getText());
                selectedFlight.setDepartureAirportIdent(departureAirportField.getText());

                // NEW: Update departure time.
                try {
                    selectedFlight.setDepartureTime(LocalTime.parse(departureTimeField.getText()));
                } catch (DateTimeParseException e) {
                    throw new IllegalArgumentException("Invalid departure time format. Please use the format HH:MM.");
                }

                selectedFlight.setArrivalAirportIdent(arrivalAirportField.getText());
                try {
                    selectedFlight.setArrivalTime(LocalTime.parse(arrivalTimeField.getText()));
                } catch (DateTimeParseException e) {
                    throw new IllegalArgumentException("Invalid arrival time format. Please use the format HH:MM.");
                }

                selectedFlight.setDayOfWeek(getSelectedDays());
                unsavedChanges = true;

                database.updateScheduledFlight(selectedFlight);

                showAlert("Update", "Flight details updated successfully. Click the \"Load\" button to refresh the table view.", Alert.AlertType.INFORMATION);
            } catch (IllegalArgumentException ex) {
                showAlert("Error", ex.getMessage(), Alert.AlertType.ERROR);
            }
        } else {
            showAlert("Warning", "No flight selected to update.", Alert.AlertType.WARNING);
        }
    }

    private void clearDetailEditor() {
        flightDesignatorField.setText("");
        departureAirportField.setText("");
        departureTimeField.setText("");
        arrivalAirportField.setText("");
        arrivalTimeField.setText("");

        mondayButton.setSelected(false);
        tuesdayButton.setSelected(false);
        wednesdayButton.setSelected(false);
        thursdayButton.setSelected(false);
        fridayButton.setSelected(false);
        saturdayButton.setSelected(false);
        sundayButton.setSelected(false);
    }

    private boolean isDuplicate(String flightDesignator, String arrivalIdent, String departureIdent) {
        for (ScheduledFlight item : flightTable.getItems()) {
            if (item.getFlightDesignator().equals(flightDesignator)
                    && item.getArrivalAirportIdent().equals(arrivalIdent)
                    && item.getDepartureAirportIdent().equals(departureIdent)) {
                return true;
            }
        }
        return false;
    }

    private void showAlert(String title, String content, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
