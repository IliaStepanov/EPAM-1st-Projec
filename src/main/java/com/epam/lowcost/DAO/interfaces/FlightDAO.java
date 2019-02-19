package com.epam.lowcost.DAO.interfaces;

import com.epam.lowcost.model.Flight;

import java.time.LocalDateTime;
import java.util.List;


public interface FlightDAO {
    List<Flight> getAllFlights();
    Flight getById(Long id);
    Flight addNewFlight(Flight flight);
    Flight deleteFlight(Long id);
    Flight updateFlight(Flight flight);
    List <Flight> getByFromToDate (String departureAirport, String arrivalAirport, LocalDateTime departureDateFrom,LocalDateTime departureDateTo);

}
