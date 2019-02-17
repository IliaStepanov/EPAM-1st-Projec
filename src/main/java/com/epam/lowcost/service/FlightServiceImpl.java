package com.epam.lowcost.service;

import com.epam.lowcost.DAO.FlightDAO;
import com.epam.lowcost.model.Flight;
import com.epam.lowcost.model.Plane;
import com.epam.lowcost.service.FlightService;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
public class FlightServiceImpl implements FlightService {
    private FlightDAO flightDAO;
    private PlaneService planeService;

    @Override
    public List<Flight> getAllFlights() {
        return flightDAO.getAllFlights();
    }

    @Override
    public Flight addNewFlight(Flight flight) {
        flight.setPlane(planeService.getById(flight.getPlane().getId()));
        return flightDAO.addNewFlight(flight);
    }

    @Override
    public Flight getById(Long id) {
        return flightDAO.getById(id);
    }

    @Override
    public Flight updateFlight(Flight flight) {
        return flightDAO.updateFlight(flight);
    }

    @Override
    public Flight deleteFlight(Long id) { return flightDAO.deleteFlight(id); }

    @Override
    public  List <Flight>  getByFromToDate(String departureAirport, String arrivalAirport, LocalDateTime departureDateFrom,LocalDateTime departureDateTo) {
        return  flightDAO.getByFromToDate(departureAirport,arrivalAirport,departureDateFrom,departureDateTo);
    }
}
