package com.epam.lowcost.service.implementations;

import com.epam.lowcost.DAO.interfaces.FlightDAO;
import com.epam.lowcost.model.Flight;
import com.epam.lowcost.service.interfaces.AirportService;
import com.epam.lowcost.service.interfaces.FlightService;
import com.epam.lowcost.service.interfaces.PlaneService;
import com.epam.lowcost.service.interfaces.TicketService;

import java.time.LocalDateTime;
import java.util.List;

import static java.time.temporal.ChronoUnit.DAYS;


public class FlightServiceImpl implements FlightService {
    public AirportService airportService;
    private FlightDAO flightDAO;
    private PlaneService planeService;
    private TicketService ticketService;

    public FlightServiceImpl(FlightDAO flightDAO, PlaneService planeService, AirportService airportService) {
        this.flightDAO = flightDAO;
        this.planeService = planeService;
        this.airportService = airportService;
    }

    public void setTicketService(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @Override
    public List<Flight> getAllFlightsWithUpdatedPrice() {
        List<Flight> flights = getAllFlights();
        flights.forEach(f -> updateFlightPrice(f));
        flights.forEach(f -> f.getPlane().setEconomPlacesNumber(getNumberOfFreeEconomyPlaces(f)));
        flights.forEach(f -> f.getPlane().setBusinessPlacesNumber(getNumberOfFreeBusinessPlaces(f)));
        return flights;
    }

    @Override
    public List<Flight> getFilteredFlightsWithUpdatedPrice(String departureAirport, String arrivalAirport, LocalDateTime departureDateFrom, LocalDateTime departureDateTo) {
        List<Flight> flights = getByFromToDate(departureAirport, arrivalAirport, departureDateFrom, departureDateTo);
        flights.forEach(f -> updateFlightPrice(f));
        flights.forEach(f -> f.getPlane().setEconomPlacesNumber(getNumberOfFreeEconomyPlaces(f)));
        flights.forEach(f -> f.getPlane().setBusinessPlacesNumber(getNumberOfFreeBusinessPlaces(f)));
        return flights;
    }

    @Override
    public Flight getFlightByIdWithUpdatedPrice(Long id) {
        Flight flight = getById(id);
        updateFlightPrice(flight);
        flight.getPlane().setEconomPlacesNumber(getNumberOfFreeEconomyPlaces(flight));
        flight.getPlane().setBusinessPlacesNumber(getNumberOfFreeBusinessPlaces(flight));
        return flight;
    }

    @Override
    public List<Flight> getAllFlights() {
        return flightDAO.getAllFlights();
    }

    @Override
    public Flight addNewFlight(Flight flight) {
        flight.setPlane(planeService.getById(flight.getPlane().getId()));
        flight.setArrivalAirport(airportService.getAirportByCode(flight.getArrivalAirport().getCode()));
        flight.setDepartureAirport(airportService.getAirportByCode(flight.getDepartureAirport().getCode()));
        return flightDAO.addNewFlight(flight);
    }

    public Flight getById(Long id) {
        return flightDAO.getById(id);

    }


    @Override
    public Flight updateFlight(Flight flight) {
        return flightDAO.updateFlight(flight);
    }


    @Override
    public String deleteFlight(Long id) {
        if (ticketService.deleteTicketsByFlightId(id)) {
            return flightDAO.deleteFlight(id);
        }
        return null;

    }

    @Override
    public List<Flight> getByFromToDate(String departureAirport, String arrivalAirport, LocalDateTime
            departureDateFrom, LocalDateTime departureDateTo) {
        return flightDAO.getByFromToDate(departureAirport, arrivalAirport, departureDateFrom, departureDateTo);
    }

    private long calculateInitialFlightPriceByDate(long daysBetween, long minPrice) {
        long daysNumber = 60;//min number of days for price rising
        long price;
        if (daysBetween > daysNumber) {
            price = minPrice;
        } else {
            price = (long) (minPrice + (daysNumber - daysBetween) * (daysNumber - daysBetween) * (minPrice / ((double) daysNumber * daysNumber)));
        }

        return price;
    }

    private void updateFlightPrice(Flight flight) {
        LocalDateTime dateAfter = flight.getDepartureDate();
        LocalDateTime dateBefore = LocalDateTime.now();
        long daysBetween = DAYS.between(dateBefore, dateAfter);
        long minPrice = flight.getInitialPrice();
        long decreaseBusinessPlacesIncreasePrice = 0;
        long decreaseEconomyPlacesIncreasePrice = 0;
        long decreaseDaysBetweenIncreasePrice = calculateInitialFlightPriceByDate(daysBetween, minPrice);
        // if number of free business places less than quarter of plane business places capacity
        // price rises on quarter of min price
        double priceChangecoefficientForBusinessPlaces = 4.0;
        double priceChangecoefficientForEconomyPlaces = 10.0;
        if (getNumberOfFreeBusinessPlaces(flight) < flight.getPlane().getBusinessPlacesNumber() / priceChangecoefficientForBusinessPlaces) {
            decreaseBusinessPlacesIncreasePrice = (long) (minPrice / priceChangecoefficientForBusinessPlaces);
        }
        // the same with economy places, but coefficient = 10
        if (getNumberOfFreeEconomyPlaces(flight) < flight.getPlane().getEconomPlacesNumber() / priceChangecoefficientForEconomyPlaces) {
            decreaseEconomyPlacesIncreasePrice = (long) (minPrice / priceChangecoefficientForEconomyPlaces);
        }

        flight.setInitialPrice(decreaseBusinessPlacesIncreasePrice +
                decreaseDaysBetweenIncreasePrice + decreaseEconomyPlacesIncreasePrice);

    }

    private int getNumberOfFreeBusinessPlaces(Flight flight) {
        int totalNumber = flight.getPlane().getBusinessPlacesNumber();
        int holdPlaces = ticketService.numberBoughtPlaces(flight.getId(), true);
        return totalNumber - holdPlaces;
    }


    private int getNumberOfFreeEconomyPlaces(Flight flight) {
        int totalNumber = flight.getPlane().getEconomPlacesNumber();
        int holdPlaces = ticketService.numberBoughtPlaces(flight.getId(), false);
        return totalNumber - holdPlaces;
    }


}
