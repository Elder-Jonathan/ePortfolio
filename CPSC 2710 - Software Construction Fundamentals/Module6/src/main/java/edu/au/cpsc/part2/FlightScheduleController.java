package edu.au.cpsc.part2;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.*;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Window;

import java.io.*;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.HashSet;
import java.util.Locale;

public class FlightScheduleController {

    private final FlightUIModel flightUIModel = new FlightUIModel();
    private final AirlineDatabase database = AirlineDatabaseIO.getDatabase();
    private final BooleanProperty isItemAdded = new SimpleBooleanProperty(false);

    // Observable list (and filtered/sorted wrappers) that backs the table.
    private ObservableList<ScheduledFlight> flightList = FXCollections.observableArrayList();
    private FilteredList<ScheduledFlight> filteredData;

    // FXML – Table and its columns.
    @FXML
    private TableView<ScheduledFlight> flightTable;
    @FXML
    private TableColumn<ScheduledFlight, String> flightDesignatorColumn;
    @FXML
    private TableColumn<ScheduledFlight, String> departureAirportColumn;
    @FXML
    private TableColumn<ScheduledFlight, String> arrivalAirportColumn;
    @FXML
    private TableColumn<ScheduledFlight, String> dayOfWeekColumn;

    // FXML – Detail editor fields.
    @FXML
    private TextField flightDesignatorField;
    @FXML
    private TextField departureAirportField;
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

    // FXML – Action buttons.
    @FXML
    private Button addButton;
    @FXML
    private Button updateButton;
    @FXML
    private Button removeButton;
    @FXML
    private Button saveButton;
    @FXML
    private Button loadButton;
    @FXML
    private Button importCSVButton;
    @FXML
    private Button exportCSVButton;

    // FXML – Additional controls for search and filtering.
    @FXML
    private TextField searchField;
    @FXML
    private ComboBox<String> dayFilterComboBox;

    @FXML
    public void initialize() {
        // Make the table responsive: columns auto-resize.
        flightTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        // Set up table columns.
        flightDesignatorColumn.setCellValueFactory(new PropertyValueFactory<>("flightDesignator"));
        departureAirportColumn.setCellValueFactory(new PropertyValueFactory<>("departureAirportIdent"));
        arrivalAirportColumn.setCellValueFactory(new PropertyValueFactory<>("arrivalAirportIdent"));
        dayOfWeekColumn.setCellValueFactory(new PropertyValueFactory<>("dayOfWeek"));

        // Bind text fields to the UI model.
        flightDesignatorField.textProperty().bindBidirectional(flightUIModel.flightDesignatorProperty());
        departureAirportField.textProperty().bindBidirectional(flightUIModel.departureAirportIdentProperty());
        arrivalAirportField.textProperty().bindBidirectional(flightUIModel.arrivalAirportIdentProperty());

        // Disable buttons initially.
        addButton.setDisable(true);
        updateButton.setDisable(true);
        removeButton.setDisable(true);
        saveButton.setDisable(true);
        loadButton.setDisable(false);

        // Enable the add button only when all required fields are filled.
        BooleanBinding allFieldsFilled = Bindings.createBooleanBinding(() ->
                        !flightDesignatorField.getText().trim().isEmpty() &&
                                !departureAirportField.getText().trim().isEmpty() &&
                                !arrivalAirportField.getText().trim().isEmpty() &&
                                !arrivalTimeField.getText().trim().isEmpty(),
                flightDesignatorField.textProperty(), departureAirportField.textProperty(),
                arrivalAirportField.textProperty(), arrivalTimeField.textProperty()
        );
        addButton.disableProperty().bind(allFieldsFilled.not());
        saveButton.disableProperty().bind(isItemAdded.not());

        // Updated inline validation style bindings for text fields.
        // When valid, use a green background, green border, dropshadow effect, white text, and larger font.
        flightDesignatorField.styleProperty().bind(
                Bindings.when(flightUIModel.flightDesignatorValid())
                        .then("-fx-background-color: rgba(0,128,0,0.3); " +
                                "-fx-border-color: #00FF00; -fx-border-width: 2; " +
                                "-fx-effect: dropshadow(gaussian, rgba(0,255,0,0.5), 5, 0, 0, 0); " +
                                "-fx-text-fill: white; -fx-font-size: 16px;")
                        .otherwise("-fx-background-color: rgba(255,0,0,0.3); " +
                                "-fx-border-color: #FF0000; -fx-border-width: 2; " +
                                "-fx-effect: dropshadow(gaussian, rgba(255,0,0,0.5), 5, 0, 0, 0); " +
                                "-fx-text-fill: white; -fx-font-size: 16px;")
        );
        // Do similar for the other text fields.
        departureAirportField.textProperty().addListener((obs, oldVal, newVal) ->
                flightUIModel.validateDepartureAirportIdent());
        departureAirportField.styleProperty().bind(
                Bindings.when(flightUIModel.departureAirportIdentValidProperty())
                        .then("-fx-background-color: rgba(0,128,0,0.3); " +
                                "-fx-border-color: #00FF00; -fx-border-width: 2; " +
                                "-fx-effect: dropshadow(gaussian, rgba(0,255,0,0.5), 5, 0, 0, 0); " +
                                "-fx-text-fill: white; -fx-font-size: 16px;")
                        .otherwise("-fx-background-color: rgba(255,0,0,0.3); " +
                                "-fx-border-color: #FF0000; -fx-border-width: 2; " +
                                "-fx-effect: dropshadow(gaussian, rgba(255,0,0,0.5), 5, 0, 0, 0); " +
                                "-fx-text-fill: white; -fx-font-size: 16px;")
        );
        arrivalAirportField.textProperty().addListener((obs, oldVal, newVal) ->
                flightUIModel.validateArrivalAirportIdent());
        arrivalAirportField.styleProperty().bind(
                Bindings.when(flightUIModel.arrivalAirportIdentValidProperty())
                        .then("-fx-background-color: rgba(0,128,0,0.3); " +
                                "-fx-border-color: #00FF00; -fx-border-width: 2; " +
                                "-fx-effect: dropshadow(gaussian, rgba(0,255,0,0.5), 5, 0, 0, 0); " +
                                "-fx-text-fill: white; -fx-font-size: 16px;")
                        .otherwise("-fx-background-color: rgba(255,0,0,0.3); " +
                                "-fx-border-color: #FF0000; -fx-border-width: 2; " +
                                "-fx-effect: dropshadow(gaussian, rgba(255,0,0,0.5), 5, 0, 0, 0); " +
                                "-fx-text-fill: white; -fx-font-size: 16px;")
        );
        arrivalTimeField.textProperty().addListener((obs, oldVal, newVal) ->
                flightUIModel.validateArrivalTime());
        arrivalTimeField.styleProperty().bind(
                Bindings.when(flightUIModel.arrivalTimeValidProperty())
                        .then("-fx-background-color: rgba(0,128,0,0.3); " +
                                "-fx-border-color: #00FF00; -fx-border-width: 2; " +
                                "-fx-effect: dropshadow(gaussian, rgba(0,255,0,0.5), 5, 0, 0, 0); " +
                                "-fx-text-fill: white; -fx-font-size: 16px;")
                        .otherwise("-fx-background-color: rgba(255,0,0,0.3); " +
                                "-fx-border-color: #FF0000; -fx-border-width: 2; " +
                                "-fx-effect: dropshadow(gaussian, rgba(255,0,0,0.5), 5, 0, 0, 0); " +
                                "-fx-text-fill: white; -fx-font-size: 16px;")
        );

        flightUIModel.flightDesignatorProperty().addListener((obs, oldVal, newVal) ->
                flightUIModel.flightDesignatorValid());

        // When a flight is selected, populate the detail editor.
        flightTable.getSelectionModel().selectedItemProperty().addListener((obs, oldFlight, newFlight) -> {
            if (newFlight != null) {
                populateDetailEditor(newFlight);
                updateButton.setDisable(false);
                removeButton.setDisable(false);
            } else {
                updateButton.setDisable(true);
                removeButton.setDisable(true);
            }
        });

        // Set up search and filter controls.
        searchField.setPromptText("Search flights...");
        dayFilterComboBox.getItems().addAll("All", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday");
        dayFilterComboBox.setValue("All");

        // Load data from the database.
        flightList.addAll(database.getFlights());
        filteredData = new FilteredList<>(flightList, p -> true);
        SortedList<ScheduledFlight> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(flightTable.comparatorProperty());
        flightTable.setItems(sortedData);

        // Listen for changes in search text and day filter.
        searchField.textProperty().addListener((obs, oldVal, newVal) -> updateFilters());
        dayFilterComboBox.valueProperty().addListener((obs, oldVal, newVal) -> updateFilters());
    }

    /** Updates the filtered data predicate based on search text and day filter. */
    private void updateFilters() {
        String searchText = searchField.getText().toLowerCase().trim();
        String dayFilter = dayFilterComboBox.getValue();
        filteredData.setPredicate(flight -> {
            boolean matchesSearch = searchText.isEmpty() ||
                    flight.getFlightDesignator().toLowerCase().contains(searchText) ||
                    flight.getDepartureAirportIdent().toLowerCase().contains(searchText) ||
                    flight.getArrivalAirportIdent().toLowerCase().contains(searchText);
            boolean matchesDay = dayFilter.equals("All") ||
                    flight.getDayOfWeek().contains(getDayCode(dayFilter));
            return matchesSearch && matchesDay;
        });
    }

    /** Converts day names to their one-letter codes. */
    private String getDayCode(String dayName) {
        switch(dayName.toLowerCase(Locale.ENGLISH)){
            case "monday": return "M";
            case "tuesday": return "T";
            case "wednesday": return "W";
            case "thursday": return "R";
            case "friday": return "F";
            case "saturday": return "S";
            case "sunday": return "U";
            default: return "";
        }
    }

    /** Populates the detail editor fields with the selected flight’s data. */
    private void populateDetailEditor(ScheduledFlight flight) {
        flightDesignatorField.setText(flight.getFlightDesignator());
        departureAirportField.setText(flight.getDepartureAirportIdent());
        arrivalAirportField.setText(flight.getArrivalAirportIdent());
        arrivalTimeField.setText(flight.getArrivalTime().toString());
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
        String flightDesignator = flightDesignatorField.getText().trim();
        String departureIdent = departureAirportField.getText().trim();
        String arrivalIdent = arrivalAirportField.getText().trim();

        if (isDuplicate(flightDesignator, arrivalIdent, departureIdent)) {
            showAlert("Duplicate Entry", "This flight already exists! Please update the existing entry.", AlertType.ERROR);
            return;
        }

        try {
            ScheduledFlight flight = new ScheduledFlight();
            flight.setFlightDesignator(flightDesignator);
            flight.setDepartureAirportIdent(departureIdent);
            flight.setArrivalAirportIdent(arrivalIdent);
            try {
                flight.setArrivalTime(LocalTime.parse(arrivalTimeField.getText().trim()));
            } catch (DateTimeParseException e) {
                throw new IllegalArgumentException("Invalid time format. Please use HH:MM (24-hour).");
            }
            flight.setDayOfWeek(getSelectedDays());
            flightList.add(flight);
            clearDetailEditor();
            isItemAdded.set(true);
        } catch (IllegalArgumentException ex) {
            showAlert("Error", ex.getMessage(), AlertType.ERROR);
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
        database.setFlights(new HashSet<>(flightList));
        AirlineDatabaseIO.saveDatabase();
        showAlert("Success", "Flight schedule successfully saved.", AlertType.INFORMATION);
        isItemAdded.set(false);
    }

    @FXML
    public void RemoveButtonAction(ActionEvent event) {
        ScheduledFlight selectedFlight = flightTable.getSelectionModel().getSelectedItem();
        if (selectedFlight != null) {
            flightList.remove(selectedFlight);
            database.removeFlight(selectedFlight);
            AirlineDatabaseIO.saveDatabase();
            showAlert("Success", "Flight removed successfully.", AlertType.INFORMATION);
        } else {
            showAlert("Warning", "No flight selected to remove.", AlertType.WARNING);
        }
        clearDetailEditor();
    }

    @FXML
    public void LoadButtonAction(ActionEvent event) {
        clearDetailEditor();
        try {
            AirlineDatabase loadedDb = AirlineDatabaseIO.getDatabase();
            flightList.clear();
            flightList.addAll(loadedDb.getFlights());
            showAlert("Success", "Database loaded successfully.", AlertType.INFORMATION);
        } catch (Exception ex) {
            ex.printStackTrace();
            showAlert("Error", "Error loading database: " + ex.getMessage(), AlertType.ERROR);
        }
    }

    @FXML
    public void UpdateButtonAction(ActionEvent event) {
        ScheduledFlight selectedFlight = flightTable.getSelectionModel().getSelectedItem();
        if (selectedFlight != null) {
            try {
                selectedFlight.setFlightDesignator(flightDesignatorField.getText().trim());
                selectedFlight.setDepartureAirportIdent(departureAirportField.getText().trim());
                selectedFlight.setArrivalAirportIdent(arrivalAirportField.getText().trim());
                selectedFlight.setArrivalTime(LocalTime.parse(arrivalTimeField.getText().trim()));
                selectedFlight.setDayOfWeek(getSelectedDays());
                database.updateScheduledFlight(selectedFlight);
                flightTable.refresh();
                showAlert("Update", "Flight details updated successfully.", AlertType.INFORMATION);
            } catch (IllegalArgumentException | DateTimeParseException ex) {
                showAlert("Error", "Update failed: " + ex.getMessage(), AlertType.ERROR);
            }
        } else {
            showAlert("Warning", "No flight selected to update.", AlertType.WARNING);
        }
    }

    @FXML
    public void importCSVAction(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Import Flight Schedules");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));
        Window stage = importCSVButton.getScene().getWindow();
        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;
                int count = 0;
                while ((line = br.readLine()) != null) {
                    String[] tokens = line.split(",");
                    if (tokens.length >= 5) {
                        ScheduledFlight flight = new ScheduledFlight();
                        flight.setFlightDesignator(tokens[0].trim());
                        flight.setDepartureAirportIdent(tokens[1].trim());
                        flight.setArrivalAirportIdent(tokens[2].trim());
                        flight.setArrivalTime(LocalTime.parse(tokens[3].trim()));
                        // Days should be separated by semicolons (e.g., "M;W;F")
                        HashSet<String> days = new HashSet<>();
                        for (String day : tokens[4].split(";")) {
                            days.add(day.trim());
                        }
                        flight.setDayOfWeek(days);
                        if (!isDuplicate(flight.getFlightDesignator(), flight.getArrivalAirportIdent(), flight.getDepartureAirportIdent())) {
                            flightList.add(flight);
                            count++;
                        }
                    }
                }
                showAlert("Import Success", count + " flights imported successfully.", AlertType.INFORMATION);
            } catch (Exception e) {
                showAlert("Import Error", "Error importing CSV: " + e.getMessage(), AlertType.ERROR);
            }
        }
    }

    @FXML
    public void exportCSVAction(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Export Flight Schedules");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));
        Window stage = exportCSVButton.getScene().getWindow();
        File file = fileChooser.showSaveDialog(stage);
        if (file != null) {
            try (PrintWriter writer = new PrintWriter(new FileWriter(file))) {
                writer.println("FlightDesignator,DepartureAirport,ArrivalAirport,ArrivalTime,Days");
                for (ScheduledFlight flight : flightList) {
                    String days = String.join(";", flight.getDayOfWeek());
                    writer.printf("%s,%s,%s,%s,%s%n",
                            flight.getFlightDesignator(),
                            flight.getDepartureAirportIdent(),
                            flight.getArrivalAirportIdent(),
                            flight.getArrivalTime().toString(),
                            days);
                }
                showAlert("Export Success", "Flights exported successfully.", AlertType.INFORMATION);
            } catch (IOException e) {
                showAlert("Export Error", "Error exporting CSV: " + e.getMessage(), AlertType.ERROR);
            }
        }
    }

    private boolean isDuplicate(String flightDesignator, String arrivalIdent, String departureIdent) {
        for (ScheduledFlight item : flightList) {
            if (item.getFlightDesignator().equalsIgnoreCase(flightDesignator) &&
                    item.getArrivalAirportIdent().equalsIgnoreCase(arrivalIdent) &&
                    item.getDepartureAirportIdent().equalsIgnoreCase(departureIdent)) {
                return true;
            }
        }
        return false;
    }

    private void clearDetailEditor() {
        flightDesignatorField.clear();
        departureAirportField.clear();
        arrivalAirportField.clear();
        arrivalTimeField.clear();
        mondayButton.setSelected(false);
        tuesdayButton.setSelected(false);
        wednesdayButton.setSelected(false);
        thursdayButton.setSelected(false);
        fridayButton.setSelected(false);
        saturdayButton.setSelected(false);
        sundayButton.setSelected(false);
    }

    private void showAlert(String title, String content, AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
