package com.epam.lowcost.DAO;

import com.epam.lowcost.model.Flight;

import java.util.List;


public interface FlightDAO {
    List<Flight> getAllFlights();
    Flight getFlightById(Long id);
    Flight addNewFlight(Flight flight);
    Flight deleteFlight(Long id);
    Flight updateFlight(Flight flight);

}
