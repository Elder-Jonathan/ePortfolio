package edu.au.cpsc.part1;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class Part1Controller {

  @FXML
  private TextField messageTextField, echoTextField, firstBidirectionalTextField, secondBidirectionalTextField;

  @FXML
  private ImageView secretOverlayImageView;

  @FXML
  private Slider secretSlider;

  @FXML
  private CheckBox selectMeCheckBox;

  @FXML
  private Label selectMeLabel;

  @FXML
  private TextField tweetTextField;

  @FXML
  private Label numberOfCharactersLabel, validityLabel;

  // Additional demonstration: A label to show an uppercase version of the message.
  // (Make sure to add a corresponding node in your FXML file if you plan to use this.)
  @FXML
  private Label upperCaseLabel;

  public void initialize() {
    // Bind the text of echoTextField to messageTextField so that changes are mirrored immediately.
    echoTextField.textProperty().bind(messageTextField.textProperty());

    // Create a bi-directional binding between two text fields.
    firstBidirectionalTextField.textProperty().bindBidirectional(secondBidirectionalTextField.textProperty());

    // Bind the opacity of the image to the slider's value.
    // (Ensure your slider’s min/max values are set appropriately, e.g., 0.0 to 1.0.)
    secretOverlayImageView.opacityProperty().bind(secretSlider.valueProperty());

    // Bind the selectMeLabel's text to the checkbox's selected state, converting the boolean to a string.
    selectMeLabel.textProperty().bind(selectMeCheckBox.selectedProperty().asString());

    // Bind a label to show the number of characters in the tweet text field using formatted binding.
    numberOfCharactersLabel.textProperty().bind(
            Bindings.format("Character count: %d", tweetTextField.textProperty().length())
    );

    // Bind the validityLabel to show "Valid" if the tweet is 10 or fewer characters, otherwise "Invalid".
    validityLabel.textProperty().bind(
            Bindings.when(tweetTextField.textProperty().length().lessThanOrEqualTo(10))
                    .then("Valid")
                    .otherwise("Invalid")
    );

    // Additionally, bind the style of the validityLabel so that it turns green when valid and red when invalid.
    validityLabel.styleProperty().bind(
            Bindings.when(tweetTextField.textProperty().length().lessThanOrEqualTo(10))
                    .then("-fx-text-fill: green;")
                    .otherwise("-fx-text-fill: red;")
    );

    // Additional demonstration: Bind a label to display an uppercase version of the message.
    if (upperCaseLabel != null) {
      upperCaseLabel.textProperty().bind(
              Bindings.createStringBinding(
                      () -> messageTextField.getText().toUpperCase(),
                      messageTextField.textProperty()
              )
      );
    }

    // Listener demonstration: Log to the console whenever the checkbox's selected state changes.
    selectMeCheckBox.selectedProperty().addListener((obs, oldValue, newValue) ->
            System.out.println("Checkbox selected: " + newValue)
    );
  }
}
