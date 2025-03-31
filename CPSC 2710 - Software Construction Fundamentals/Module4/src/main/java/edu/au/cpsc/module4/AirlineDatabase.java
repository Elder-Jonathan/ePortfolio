package edu.au.cpsc.module4;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class AirlineDatabase implements Serializable {
    private final List<ScheduledFlight> flights;

    public AirlineDatabase() {
        flights = new ArrayList<>();
    }

    public List<ScheduledFlight> getFlights() {
        return flights;
    }

    public void addScheduledFlight(ScheduledFlight sf) {
        if (sf == null) {
            throw new IllegalArgumentException("Cannot add a null ScheduledFlight.");
        }
        flights.add(sf);
    }

    public void removeScheduledFlight(ScheduledFlight sf) {
        flights.remove(sf);
    }

    public void updateScheduledFlight(ScheduledFlight updatedFlight) {
        // Remove the old version (using equals) and add the updated one.
        flights.remove(updatedFlight);
        flights.add(updatedFlight);
    }

    /**
     * Replace the current flights with the new set.
     */
    public void setFlights(Set<ScheduledFlight> newFlights) {
        flights.clear();
        flights.addAll(newFlights);
    }
}
