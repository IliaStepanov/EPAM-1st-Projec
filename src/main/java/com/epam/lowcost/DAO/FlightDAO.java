package com.epam.lowcost.DAO;

import com.epam.lowcost.model.Flight;

import java.util.List;


public interface FlightDAO {
    List<Flight> getAllFlights();
    Flight getFlightById(long id);
    Flight addNewFlight(Flight flight);
    //void deleteFlight(int id);
    Flight updateFlight(Flight flight);
}
