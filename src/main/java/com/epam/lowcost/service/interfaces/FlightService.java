package com.epam.lowcost.service.interfaces;

import com.epam.lowcost.model.Flight;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface FlightService {

    List<Flight> getFilteredFlightsWithUpdatedPrice(String departureAirport, String arrivalAirport, LocalDateTime departureDateFrom, LocalDateTime departureDateTo);

    Flight getFlightByIdWithUpdatedPrice(Long id);

    Flight getById(Long id);

    Flight addNewFlight(Flight flight);

    Flight updateFlight(Flight flight);

    String deleteFlight(Long id);

    List<Flight> getByFromToDate(String departureAirport, String arrivalAirport, LocalDateTime departureDate, LocalDateTime arrivalDate);

    Map<String, Object> getFlightsByPage(int pageId, int flightsByPage);

    Map<String, Object> getAllFlightsWithUpdatedPrice(int pageId, int numberOfFlightsOnPage);

    int countFlights();
}
