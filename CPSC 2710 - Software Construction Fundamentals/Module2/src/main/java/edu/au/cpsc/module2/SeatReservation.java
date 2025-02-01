package edu.au.cpsc.module2;

import java.time.LocalDate;

/**
 * Software Construction Fundamentals Module 2
 * @author Jonathan Elder
 * SeatReservation.java
 */
public class SeatReservation {

    // Private instance variables.
    private String flightDesignator;
    private LocalDate flightDate;
    private String firstName;
    private String lastName;
    private int numberOfPassengers; // Renamed from numberOfBags.
    private boolean flyingWithInfant;

    // Constructor for setting up a SeatReservation object.
    public SeatReservation() {
    }

    // Getter and setter methods for flightDesignator.
    public String getFlightDesignator() {
        return flightDesignator;
    }
    public void setFlightDesignator(String fd) {
        // Check if the flight designator is null or its length is not between 4 and 6 characters.
        if (fd == null || fd.length() < 4 || fd.length() > 6) {
            throw new IllegalArgumentException("Flight designator must not be null and must have 4 to 6 characters.");
        }
        flightDesignator = fd;
    }

    // Getter and setter methods for flightDate.
    public LocalDate getFlightDate() {
        return flightDate;
    }
    public void setFlightDate(LocalDate date) {
        flightDate = date;
    }

    // Getter and setter methods for firstName.
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String fn) {
        firstName = fn;
    }

    // Getter and setter methods for lastName.
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String ln) {
        lastName = ln;
    }

    // Getter and setter methods for numberOfPassengers.
    public int getNumberOfPassengers() {
        return numberOfPassengers;
    }
    public void setNumberOfPassengers(int passengers) {
        numberOfPassengers = passengers;
    }

    // Boolean response for flying with an infant.
    public boolean isFlyingWithInfant() {
        return flyingWithInfant;
    }
    public void makeFlyingWithInfant() {
        flyingWithInfant = true;
    } // Sets flyingWithInfant to true.
    public void makeNotFlyingWithInfant() {
        flyingWithInfant = false;
    } // Sets flyingWithInfant to false.

    // toString method for representing a SeatReservation object.
    @Override
    public String toString() {
        return "SeatReservation{" +
                "flightDesignator='" + flightDesignator + '\'' +
                ", flightDate=" + flightDate +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", numberOfPassengers=" + numberOfPassengers +
                ", flyingWithInfant=" + flyingWithInfant +
                '}';
    }
}