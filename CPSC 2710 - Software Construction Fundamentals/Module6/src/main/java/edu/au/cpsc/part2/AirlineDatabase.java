package edu.au.cpsc.part2;

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

    public void removeFlight(ScheduledFlight flight) {
        flights.remove(flight);
    }

    public void setFlights(Set<ScheduledFlight> newFlights) {
        this.flights.clear();
        this.flights.addAll(newFlights);
    }

    public void updateScheduledFlight(ScheduledFlight updatedFlight) {
        flights.remove(updatedFlight);
        flights.add(updatedFlight);
    }
}
