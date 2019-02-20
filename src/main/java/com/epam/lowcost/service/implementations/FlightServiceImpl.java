package com.epam.lowcost.service.implementations;

import com.epam.lowcost.DAO.interfaces.FlightDAO;
import com.epam.lowcost.model.Flight;
import com.epam.lowcost.service.interfaces.FlightService;
import com.epam.lowcost.service.interfaces.PlaneService;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

import static java.time.temporal.ChronoUnit.DAYS;


@AllArgsConstructor
public class FlightServiceImpl implements FlightService {
    private FlightDAO flightDAO;
    private PlaneService planeService;

    @Override
    public List<Flight> getAllFlights() {
        List<Flight> flights = flightDAO.getAllFlights();
        for (Flight flight : flights) {
            flight.setInitialPrice(updateFlightPrice(flight));
        }

        return flights;
    }

    @Override
    public Flight addNewFlight(Flight flight) {
        flight.setPlane(planeService.getById(flight.getPlane().getId()));
        return flightDAO.addNewFlight(flight);
    }

    @Override
    public Flight getById(Long id) {
        Flight flight = flightDAO.getById(id);
        flight.setInitialPrice(updateFlightPrice(flight));
        return flight;
    }

    @Override
    public Flight updateFlight(Flight flight) {
        return flightDAO.updateFlight(flight);
    }

    @Override
    public Flight deleteFlight(Long id) {
        return flightDAO.deleteFlight(id);
    }

    @Override
    public List<Flight> getByFromToDate(String departureAirport, String arrivalAirport, LocalDateTime departureDateFrom, LocalDateTime departureDateTo) {
        return flightDAO.getByFromToDate(departureAirport, arrivalAirport, departureDateFrom, departureDateTo);
    }

    private long updateFlightPrice(Flight flight) {
        LocalDateTime dateAfter = flight.getDepartureDate();
        LocalDateTime dateBefore = LocalDateTime.now();
        long daysBetween = DAYS.between(dateBefore, dateAfter);
        long minPrice = flight.getInitialPrice();
        long price = (long) (minPrice + (60 - daysBetween) * (60 - daysBetween) * (minPrice / 3600.0));
        if (daysBetween > 60) {
            price = minPrice;
        }
        return price;
    }

}
