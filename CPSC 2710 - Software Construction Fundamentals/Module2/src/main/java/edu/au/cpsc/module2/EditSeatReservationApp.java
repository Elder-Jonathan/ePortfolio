package edu.au.cpsc.module2;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.time.LocalDate;

/**
 * Software Construction Fundamentals Module 2
 * @author Jonathan Elder
 * EditSeatReservationApp.java
 */
public class EditSeatReservationApp extends Application {

    // Instance variables.
    private SeatReservation seatReservation;
    private TextField flightDesignator;
    private DatePicker flightDate;
    private TextField firstName;
    private TextField lastName;
    private TextField numberOfPassengers;
    private CheckBox flyingWithInfantCheckBox;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Edit Seat Reservation");

        // Create a SeatReservation object and set default instance variables.
        seatReservation = new SeatReservation();
        seatReservation.setFlightDesignator("ABC123");
        seatReservation.setFlightDate(LocalDate.of(2023, 9, 6));
        seatReservation.setFirstName("Jonathan");
        seatReservation.setLastName("Elder");
        seatReservation.setNumberOfBags(1);
        seatReservation.makeFlyingWithInfant();

        // Create UI components.
        flightDesignator = new TextField();
        flightDate = new DatePicker(LocalDate.now());
        firstName = new TextField();
        lastName = new TextField();
        numberOfPassengers = new TextField("1");
        numberOfPassengers.setEditable(false);
        flyingWithInfantCheckBox = new CheckBox("Flying with Infant?");
        Button cancelButton = new Button("Cancel");
        Button saveButton = new Button("Save");

        // Set up the event handler for the "Flying with Infant" Checkbox.
        flyingWithInfantCheckBox.setOnAction(event -> {
            if (flyingWithInfantCheckBox.isSelected()) {
                numberOfPassengers.setText("2");
            } else {
                numberOfPassengers.setText("1");
            }
        });

        // Set up the event handler for the "Save" button.
        saveButton.setOnAction(event -> {
            try {
                // Populate the seatReservation instance using values from input controls.
                seatReservation.setFlightDesignator(flightDesignator.getText());
                seatReservation.setFlightDate(flightDate.getValue());
                seatReservation.setFirstName(firstName.getText());
                seatReservation.setLastName(lastName.getText());
                seatReservation.setNumberOfBags(Integer.parseInt(numberOfPassengers.getText()));
                if (flyingWithInfantCheckBox.isSelected()) {
                    seatReservation.makeFlyingWithInfant();
                } else {
                    seatReservation.makeNotFlyingWithInfant();
                }

                //
                System.out.println("Updated Seat Reservation:");
                System.out.println(seatReservation);

            } catch (IllegalArgumentException e) {
                // Display an error message if an IllegalArgumentException occurs.
                System.out.println("Error: " + e.getMessage());
            }
            Platform.exit();
        });

        // Creates a grid pane to display the created objects.
        GridPane gridVariables = new GridPane();
        gridVariables.setHgap(10);
        gridVariables.setVgap(10);
        gridVariables.addRow(0, new Label("Flight Designator:"), flightDesignator);
        gridVariables.addRow(1, new Label("Flight Date:"), flightDate);
        gridVariables.addRow(2, new Label("First Name:"), firstName);
        gridVariables.addRow(3, new Label("Last Name:"), lastName);
        gridVariables.addRow(4, new Label("Number of Passengers:"), numberOfPassengers);
        gridVariables.addRow(5, new Label("Flying with Infant?"), flyingWithInfantCheckBox);

        HBox buttonBox = new HBox(10);
        buttonBox.setAlignment(Pos.TOP_RIGHT);
        buttonBox.getChildren().addAll(cancelButton, saveButton);

        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(gridVariables);
        borderPane.setBottom(buttonBox);

        updateUI();

        Scene scene = new Scene(borderPane, 380, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void updateUI() {
        flightDesignator.setText(seatReservation.getFlightDesignator());
        flightDate.setValue(seatReservation.getFlightDate());
        firstName.setText(seatReservation.getFirstName());
        lastName.setText(seatReservation.getLastName());
        numberOfPassengers.setText(String.valueOf(seatReservation.getNumberOfBags()));
        flyingWithInfantCheckBox.setSelected(seatReservation.isFlyingWithInfant());
    }

    public static void main(String[] args) {
        launch(args);
    }
}
