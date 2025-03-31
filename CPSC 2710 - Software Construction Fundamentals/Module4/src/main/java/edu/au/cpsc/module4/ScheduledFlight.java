package edu.au.cpsc.module4;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Objects;

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
            throw new IllegalArgumentException("The Flight Designator cannot be null.");
        }
        // Correct Validator for all flight designators. This should cover any valid entry.
        if (!flightDesignator.matches("^[A-Z]{2}\\d{1,4}$") && !flightDesignator.matches("^[A-Z]{3}\\d{1,4}$")) {
            throw new IllegalArgumentException("Invalid Flight Designator. It should have two or three uppercase letters followed by 1-4 digits.");
        }
        this.flightDesignator = flightDesignator;
    }

    public String getDepartureAirportIdent() {
        return departureAirportIdent;
    }

    public void setDepartureAirportIdent(String departureAirportIdent) {
        if (departureAirportIdent == null) {
            throw new IllegalArgumentException("The Departure Airport Ident cannot be null.");
        }
        if (!departureAirportIdent.matches("^[A-Z]{3}$")) {
            throw new IllegalArgumentException("Invalid Departure Airport Ident. It should be a valid IATA code with three uppercase letters.");
        }
        this.departureAirportIdent = departureAirportIdent;
    }

    public LocalTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalTime departureTime) {
        if (departureTime == null) {
            throw new IllegalArgumentException("The Departure Time cannot be null. Please enter a proper time in the format HH:MM.");
        }
        this.departureTime = departureTime;
    }

    public String getArrivalAirportIdent() {
        return arrivalAirportIdent;
    }

    public void setArrivalAirportIdent(String arrivalAirportIdent) {
        if (arrivalAirportIdent == null) {
            throw new IllegalArgumentException("The Arrival Airport Ident cannot be null.");
        }
        if (!arrivalAirportIdent.matches("^[A-Z]{3}$")) {
            throw new IllegalArgumentException("Invalid Arrival Airport Ident. It should be a valid IATA code with three uppercase letters.");
        }
        this.arrivalAirportIdent = arrivalAirportIdent;
    }

    public LocalTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalTime arrivalTime) {
        if (arrivalTime == null) {
            throw new IllegalArgumentException("The Arrival Time cannot be null. Please enter a proper arrival time in the format HH:MM.");
        }
        this.arrivalTime = arrivalTime;
    }

    public HashSet<String> getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(HashSet<String> dayOfWeek) {
        if (dayOfWeek == null) {
            throw new IllegalArgumentException("The Day of Week cannot be null. Please select at least one day.");
        }
        this.dayOfWeek = dayOfWeek;
    }

    /**
     * Returns the days of week as a sorted, compact string.
     * The order is M, T, W, R (Thursday), F, S, U (Sunday).
     */
    public String getDaysOfWeekString() {
        StringBuilder sb = new StringBuilder();
        String[] order = {"M", "T", "W", "R", "F", "S", "U"};
        for (String day : order) {
            if (dayOfWeek.contains(day)) {
                sb.append(day);
            }
        }
        return sb.toString();
    }

    // Override equals and hashCode so that flights can be compared properly.

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ScheduledFlight)) return false;
        ScheduledFlight that = (ScheduledFlight) o;
        return Objects.equals(flightDesignator, that.flightDesignator) &&
                Objects.equals(departureAirportIdent, that.departureAirportIdent) &&
                Objects.equals(departureTime, that.departureTime) &&
                Objects.equals(arrivalAirportIdent, that.arrivalAirportIdent) &&
                Objects.equals(arrivalTime, that.arrivalTime) &&
                Objects.equals(dayOfWeek, that.dayOfWeek);
    }

    @Override
    public int hashCode() {
        return Objects.hash(flightDesignator, departureAirportIdent, departureTime, arrivalAirportIdent, arrivalTime, dayOfWeek);
    }
}
