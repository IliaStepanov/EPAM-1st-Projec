package com.epam.lowcost.service;

import com.epam.lowcost.model.Flight;

import java.time.LocalDateTime;
import java.util.List;

public interface FlightService {
    List<Flight> getAllFlights();

    Flight getById(Long id);

    Flight addNewFlight(Flight flight);

    Flight updateFlight(Flight flight);

    Flight deleteFlight(Long id);

    List <Flight> getByFromToDate (String departureAirport, String arrivalAirport, LocalDateTime departureDate,LocalDateTime arrivalDate);
}
