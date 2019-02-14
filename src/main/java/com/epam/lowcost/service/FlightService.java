package com.epam.lowcost.service;

import com.epam.lowcost.model.Flight;

import java.util.List;

public interface FlightService {
    List<Flight> getAllFlights();

    Flight getById(Long id);

    Flight addNewFlight(Flight flight);

    Flight updateFlight(Flight flight);

    Flight deleteFlight(Long id);
}
