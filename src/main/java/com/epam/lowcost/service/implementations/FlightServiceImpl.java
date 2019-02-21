package com.epam.lowcost.service.implementations;

import com.epam.lowcost.DAO.interfaces.FlightDAO;
import com.epam.lowcost.model.Flight;
import com.epam.lowcost.service.interfaces.FlightService;
import com.epam.lowcost.service.interfaces.PlaneService;
import com.epam.lowcost.service.interfaces.TicketService;

import java.time.LocalDateTime;
import java.util.List;

public class FlightServiceImpl implements FlightService {
    private FlightDAO flightDAO;
    private PlaneService planeService;
    private TicketService ticketService;

    public FlightServiceImpl(FlightDAO flightDAO, PlaneService planeService) {
        this.flightDAO = flightDAO;
        this.planeService = planeService;
    }

    public void setTicketService (TicketService ticketService) {
        this.ticketService = ticketService;
    }

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
    public Flight deleteFlight(Long id) {
        if (ticketService.deleteTicketsByFlightId(id)) {
            return flightDAO.deleteFlight(id);
        }
        return null;
    }

    @Override
    public List<Flight> getByFromToDate(String departureAirport, String arrivalAirport, LocalDateTime departureDateFrom, LocalDateTime departureDateTo) {
        return flightDAO.getByFromToDate(departureAirport, arrivalAirport, departureDateFrom, departureDateTo);
    }
}
