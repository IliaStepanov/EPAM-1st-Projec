package com.epam.lowcost.sevice;

import com.epam.lowcost.DAO.FlightDAO;
import com.epam.lowcost.model.Flight;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class FlightServiceImpl implements FlightService {
    private FlightDAO flightDAO;

    @Override
    public List<Flight> getAllFlights() {
        return flightDAO.getAllFlights();
    }

    @Override
    public Flight addNewFlight(Flight flight) {
        return flightDAO.addNewFlight(flight);
    }

    @Override
    public Flight getFlightById(long id) {
        return flightDAO.getFlightById(id);
    }

    @Override
    public Flight updateFlight(Flight flight) {
        return flightDAO.updateFlight(flight);
    }
}
