package edu.au.cpsc.module3;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.web.WebView;
import javafx.scene.web.WebEngine;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Software Construction Fundamentals Module 3
 * @author Jonathan Elder
 * AirportController.java
 */
public class AirportController {

    // Instance variables also connected to airport-UI.fxml.
    @FXML
    private TextField identField;
    @FXML
    private TextField iataCodeField;
    @FXML
    private TextField localCodeField;
    @FXML
    private TextField typeField;
    @FXML
    private TextField nameField;
    @FXML
    private TextField elevationField;
    @FXML
    private TextField countryField;
    @FXML
    private TextField regionField;
    @FXML
    private TextField municipalityField;
    @FXML
    private WebView webView;
    private List<Airport> airports;

    public AirportController() {

        try {
            airports = Airport.readAll();
        } catch (IOException e) {
            System.err.println("Error loading airport data: " + e.getMessage());
            airports = new ArrayList<>();
        }
    }

    /**
     * Searches for an airport based on the entered ident, IATA code, or local code.
     * If a match is found, the fields are populated and the weather view is updated.
     * Otherwise, the fields are cleared and a "not found" message is displayed.
     */
    @FXML
    public void searchAirport() {
        // User input textFields for variables.
        String searchIdent = identField.getText().trim();
        String searchIata = iataCodeField.getText().trim();
        String searchLocal = localCodeField.getText().trim();

        boolean found = false;
        for (Airport airport : airports) {
            if ((!searchIdent.isEmpty() && airport.getIdent().equalsIgnoreCase(searchIdent)) ||
                    (!searchIata.isEmpty() && airport.getIataCode().equalsIgnoreCase(searchIata)) ||
                    (!searchLocal.isEmpty() && airport.getLocalCode().equalsIgnoreCase(searchLocal))) {

                // Populates the textFields with the matching airport details.
                typeField.setText(airport.getType());
                nameField.setText(airport.getName());
                elevationField.setText(airport.getElevationInFeet() != null ? airport.getElevationInFeet().toString() : "N/A");
                countryField.setText(airport.getCountry());
                regionField.setText(airport.getRegion());
                municipalityField.setText(airport.getMunicipality());

                // Update WebView if valid coordinates exist.
                if (airport.getCoordinatesLatitude() != null && airport.getCoordinatesLongitude() != null) {
                    updateWeatherView(airport.getCoordinatesLatitude(), airport.getCoordinatesLongitude());
                }
                found = true;
                break;
            }
        }

        if (!found) { // If airport was not found, clear fields and "Jedi Not Found" message.
            clearFields();
            displayNotFoundMessage();
        }
    }

    /**
     * Updates the WebView browser to windy.com using the specified coordinates.
     * @param latitude  The taken latitude variable.
     * @param longitude The taken longitude variable.
     */
    private void updateWeatherView(Double latitude, Double longitude) {
        // Pre-constructed URL with the ability to update coordinates.
        String url = String.format("https://www.windy.com/?%s,%s,12", latitude, longitude);
        WebEngine webEngine = webView.getEngine();
        webEngine.load(url);
    }

    /**
     * Clears the airport detail fields.
     */
    private void clearFields() {
        typeField.clear();
        nameField.clear();
        elevationField.clear();
        countryField.clear();
        regionField.clear();
        municipalityField.clear();
    }

    /**
     * A fun addition of showing the "Airport not found" message.
     * If the Jedi Archives for "Airports" were located at Auburn University.
     */
    private void displayNotFoundMessage() {
        String bgUrl = getClass().getResource("/edu/au/cpsc/module3/AuburnJedi.png").toExternalForm();
        String svg = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
                "<svg viewBox=\"0 0 800 800\" width=\"100%\" height=\"100%\" xmlns=\"http://www.w3.org/2000/svg\">" +
                "  <defs>" +
                "    <filter id=\"textShadow\" x=\"-50%\" y=\"-50%\" width=\"200%\" height=\"200%\">" +
                "      <feOffset in=\"SourceAlpha\" dx=\"2\" dy=\"2\" result=\"offset\"/>" +
                "      <feGaussianBlur in=\"offset\" stdDeviation=\"2\" result=\"blur\"/>" +
                "      <feMerge>" +
                "        <feMergeNode in=\"blur\"/>" +
                "        <feMergeNode in=\"SourceGraphic\"/>" +
                "      </feMerge>" +
                "    </filter>" +
                "    <style type=\"text/css\"><![CDATA[" +
                "      .text { font-family: 'Courier New', monospace; fill: #FFFFFF; filter: url(#textShadow); text-anchor: middle; }" +
                "      .message { font-size: 24px; }" +
                "      .signature { font-size: 18px; }" +
                "      .header { font-size: 36px; font-weight: bold; }" +
                "    ]]></style>" +
                "  </defs>" +
                "  <image href=\"" + bgUrl + "\" x=\"0\" y=\"0\" width=\"800\" height=\"800\" preserveAspectRatio=\"xMidYMid slice\"/>" +
                "  <rect x=\"0\" y=\"0\" width=\"800\" height=\"800\" fill=\"rgba(0, 0, 0, 0.5)\"/>" +
                "  <text x=\"400\" y=\"200\" class=\"text header\">Your airport, found it was not..</text>" +
                "  <text x=\"400\" y=\"600\" class=\"text message\">\"If the record does not exist within our archives,</text>" +
                "  <text x=\"400\" y=\"650\" class=\"text message\">it simply does not exist.\"</text>" +
                "  <text x=\"400\" y=\"700\" class=\"text signature\">&mdash; Auburn University Jedi Archive</text>" +
                "</svg>";
        webView.getEngine().loadContent(svg, "image/svg+xml");
    }
}