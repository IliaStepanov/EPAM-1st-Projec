package com.epam.lowcost.sevice;

import com.epam.lowcost.model.Flight;

import java.util.List;

public interface FlightService {
    List<Flight> getAllFlights();

    Flight getFlightById(long id);

    Flight addNewFlight(Flight flight);

    Flight updateFlight(Flight flight);
}
