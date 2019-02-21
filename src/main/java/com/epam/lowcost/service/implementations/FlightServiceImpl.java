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

    public List<Flight> getAllFlightsWithUpdatedPrice() {
        List<Flight> flights = getAllFlights();
        flights.forEach(f -> updateFlightPrice(f));

        return flights;
    }

    public Flight getFlightByIdWithUpdatedPrice(Long id) {
        Flight flight = getById(id);
        updateFlightPrice(flight);

        return flight;
    }

    @Override
    public List<Flight> getAllFlights() {
        return flightDAO.getAllFlights();
    }

    @Override
    public Flight getById(Long id) {
        return flightDAO.getById(id);
    }

    @Override
    public Flight addNewFlight(Flight flight) {
        flight.setPlane(planeService.getById(flight.getPlane().getId()));
        return flightDAO.addNewFlight(flight);
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

    private long calculateInitialFlightPriceByDate(long daysBetween, long minPrice) {
        long daysNumber = 60;//min number of days for price rising
        long price;
        if (daysBetween > daysNumber) {
            price = minPrice;
        }
        else{
            price = (long) (minPrice + (daysNumber - daysBetween) * (daysNumber - daysBetween) * (minPrice / ((double)daysNumber*daysNumber)));
        }

        return price;
    }

    private void updateFlightPrice(Flight flight){
        LocalDateTime dateAfter = flight.getDepartureDate();
        LocalDateTime dateBefore = LocalDateTime.now();
        long daysBetween = DAYS.between(dateBefore, dateAfter);
        long minPrice = flight.getInitialPrice();
        flight.setInitialPrice(calculateInitialFlightPriceByDate(daysBetween, minPrice));
    }

}
