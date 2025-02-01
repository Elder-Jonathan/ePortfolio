import java.time.LocalDate;

/**
 * Software Construction Fundamentals Module 1
 * @author Jonathan Elder
 * SeatReservation.java
 */
public class SeatReservation {

    // Private instance variables.
    private String flightDesignator;
    private LocalDate flightDate;
    private String firstName;
    private String lastName;


    // Getter and setter methods for flightDesignator.
    public String getFlightDesignator() {
        return flightDesignator;
    }
    public void setFlightDesignator(String fd) {
        flightDesignator = fd;
    }


    // Getter and setter methods for flightDate.
    public java.time.LocalDate getFlightDate() {
        return flightDate;
    }
    public void setFlightDate(java.time.LocalDate date) {
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

    @Override
    // toString method.
    public String toString() {
        return "SeatReservation{" +
                "flightDesignator=" + flightDesignator +
                ", flightDate=" + flightDate +
                ", firstName=" + (firstName != null ? "\"" + firstName + "\"" : "null") +
                ", lastName=" + (lastName != null ? "\"" + lastName + "\"" : "null") +
                '}';
    }
}
