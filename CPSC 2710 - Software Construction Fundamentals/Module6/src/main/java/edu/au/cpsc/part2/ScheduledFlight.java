package edu.au.cpsc.part2;

import java.time.LocalTime;
import java.util.HashSet;
import java.io.Serializable;

public class ScheduledFlight implements Serializable {

    private String flightDesignator;
    private String departureAirportIdent;
    private LocalTime departureTime;
    private String arrivalAirportIdent;
    private LocalTime arrivalTime;
    private HashSet<String> dayOfWeek;

    public ScheduledFlight() {
        dayOfWeek = new HashSet<>();
    }

    public String getFlightDesignator() {
        return flightDesignator;
    }

    public void setFlightDesignator(String flightDesignator) {
        if (flightDesignator == null) {
            throw new IllegalArgumentException("Flight Designator cannot be null.");
        }
        if (!flightDesignator.matches("^[A-Z]{2}\\d{1,4}$") && !flightDesignator.matches("^[A-Z]{3}\\d{1,4}$")) {
            throw new IllegalArgumentException("Invalid Flight Designator. Use 2–3 uppercase letters followed by 1–4 digits.");
        }
        this.flightDesignator = flightDesignator;
    }

    public String getDepartureAirportIdent() {
        return departureAirportIdent;
    }

    public void setDepartureAirportIdent(String departureAirportIdent) {
        if (departureAirportIdent == null) {
            throw new IllegalArgumentException("Departure Airport Ident cannot be null.");
        }
        if (!departureAirportIdent.matches("^[A-Z]{3}$")) {
            throw new IllegalArgumentException("Invalid Departure Airport Ident. Must be a 3-letter IATA code.");
        }
        this.departureAirportIdent = departureAirportIdent;
    }

    public LocalTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalTime departureTime) {
        if (departureTime == null) {
            throw new IllegalArgumentException("Departure Time cannot be null. Use format HH:MM.");
        }
        this.departureTime = departureTime;
    }

    public String getArrivalAirportIdent() {
        return arrivalAirportIdent;
    }

    public void setArrivalAirportIdent(String arrivalAirportIdent) {
        if (arrivalAirportIdent == null) {
            throw new IllegalArgumentException("Arrival Airport Ident cannot be null.");
        }
        if (!arrivalAirportIdent.matches("^[A-Z]{3}$")) {
            throw new IllegalArgumentException("Invalid Arrival Airport Ident. Must be a 3-letter IATA code.");
        }
        this.arrivalAirportIdent = arrivalAirportIdent;
    }

    public LocalTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalTime arrivalTime) {
        if (arrivalTime == null) {
            throw new IllegalArgumentException("Arrival Time cannot be null. Use format HH:MM.");
        }
        this.arrivalTime = arrivalTime;
    }

    public HashSet<String> getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(HashSet<String> dayOfWeek) {
        if (dayOfWeek == null) {
            throw new IllegalArgumentException("Day of Week cannot be null. Please select at least one day.");
        }
        this.dayOfWeek = dayOfWeek;
    }
}

