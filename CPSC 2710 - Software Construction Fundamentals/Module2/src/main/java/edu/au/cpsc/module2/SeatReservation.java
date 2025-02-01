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
    private int numberOfBags;
    private boolean flyingWithInfant;

    // Call to constructor for setting up a SeatReservation object.
    public SeatReservation() {
    }

    // Getter and setter methods for flightDesignator variable.
    public String getFlightDesignator() {
        return flightDesignator;
    }
    public void setFlightDesignator(String fd) {
        // Check if the flight designator is null or has fewer than 4 characters or more than 6 characters.
        if (fd == null || fd.length() < 4 || fd.length() > 6) {
            throw new IllegalArgumentException("Flight designator must not be null and must have 4 to 6 characters.");
        }
        flightDesignator = fd;
    }


    // Getter and setter methods for flightDate variable.
    public LocalDate getFlightDate() {
        return flightDate;
    }
    public void setFlightDate(LocalDate date) {
        flightDate = date;
    }


    // Getter and setter methods for firstName variable.
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String fn) {
        firstName = fn;
    }


    // Getter and setter methods for lastName variable.
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String ln) {
        lastName = ln;
    }


    // Getter and setter methods for numberOfBags variable.
    public int getNumberOfBags() {
        return numberOfBags;
    }
    public void setNumberOfBags(int bags) {
        numberOfBags = bags;
    }


    // Boolean response for flying with an infant.
    public boolean isFlyingWithInfant() {
        return flyingWithInfant;
    }
    public void makeFlyingWithInfant() {
        flyingWithInfant = true;
    } // If flying with an infant set to true.
    public void makeNotFlyingWithInfant() {
        flyingWithInfant = false;
    } // Other set variable to false.

    // toString method for representing a SeatReservation object.
    @Override
    public String toString() {
        return "SeatReservation{" +
                "flightDesignator='" + flightDesignator + '\'' +
                ", flightDate=" + flightDate +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", numberOfBags=" + numberOfBags +
                ", flyingWithInfant=" + flyingWithInfant +
                '}';
    }
}