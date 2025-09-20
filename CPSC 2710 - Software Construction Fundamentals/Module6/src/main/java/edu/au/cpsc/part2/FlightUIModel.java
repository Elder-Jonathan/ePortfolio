package edu.au.cpsc.part2;

import javafx.beans.property.*;
import javafx.beans.value.ObservableBooleanValue;
import java.time.LocalTime;

public class FlightUIModel {

    private final StringProperty flightDesignator = new SimpleStringProperty();
    private final StringProperty departureAirportIdent = new SimpleStringProperty();
    private final StringProperty arrivalAirportIdent = new SimpleStringProperty();
    private final ObjectProperty<LocalTime> arrivalTime = new SimpleObjectProperty<>();

    private final BooleanProperty flightDesignatorValid = new SimpleBooleanProperty(true);
    private final BooleanProperty departureAirportIdentValid = new SimpleBooleanProperty(true);
    private final BooleanProperty arrivalAirportIdentValid = new SimpleBooleanProperty(true);
    private final BooleanProperty arrivalTimeValid = new SimpleBooleanProperty(true);

    public FlightUIModel() {
        // Additional bindings can be added if needed.
    }

    public StringProperty flightDesignatorProperty() {
        return flightDesignator;
    }

    public StringProperty departureAirportIdentProperty() {
        return departureAirportIdent;
    }

    public StringProperty arrivalAirportIdentProperty() {
        return arrivalAirportIdent;
    }

    public ObservableBooleanValue flightDesignatorValid() {
        String designator = flightDesignator.get();
        if (designator == null || designator.isEmpty()) {
            flightDesignatorValid.set(true);
        } else if (!designator.matches("^[A-Z]{2}\\d{1,4}$") && !designator.matches("^[A-Z]{3}\\d{1,4}$")) {
            flightDesignatorValid.set(false);
        } else {
            flightDesignatorValid.set(true);
        }
        return flightDesignatorValid;
    }

    public void validateDepartureAirportIdent() {
        String ident = departureAirportIdent.get();
        if (ident == null || ident.isEmpty() || ident.matches("^[A-Z]{3}$")) {
            departureAirportIdentValid.set(true);
        } else {
            departureAirportIdentValid.set(false);
        }
    }

    public BooleanProperty departureAirportIdentValidProperty() {
        return departureAirportIdentValid;
    }

    public void validateArrivalAirportIdent() {
        String ident = arrivalAirportIdent.get();
        if (ident == null || ident.isEmpty() || ident.matches("^[A-Z]{3}$")) {
            arrivalAirportIdentValid.set(true);
        } else {
            arrivalAirportIdentValid.set(false);
        }
    }

    public BooleanProperty arrivalAirportIdentValidProperty() {
        return arrivalAirportIdentValid;
    }

    public void validateArrivalTime() {
        String time = (arrivalTime.get() != null) ? arrivalTime.get().toString() : "";
        if (time.isEmpty() || time.matches("^([01]\\d|2[0-3]):([0-5]\\d)$")) {
            arrivalTimeValid.set(true);
        } else {
            arrivalTimeValid.set(false);
        }
    }

    public BooleanProperty arrivalTimeValidProperty() {
        return arrivalTimeValid;
    }
}
