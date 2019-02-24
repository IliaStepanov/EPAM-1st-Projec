package com.epam.lowcost.controller;

import com.epam.lowcost.model.Airport;
import com.epam.lowcost.model.Flight;
import com.epam.lowcost.model.Plane;
import com.epam.lowcost.service.implementations.FlightServiceImpl;
import com.epam.lowcost.service.interfaces.AirportService;
import com.epam.lowcost.service.interfaces.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;

import static com.epam.lowcost.util.EndPoints.*;


@Controller
@RequestMapping(value = FLIGHTS)
public class FlightController {

    FlightService flightService;
    AirportService airportService;

    @Autowired
    public FlightController(FlightService flightService, AirportService airportService) {

        this.flightService = flightService;
        this.airportService = airportService;
    }

    @GetMapping(value = ALL)
    public String getAllFlights(Model model) {
        model.addAttribute("flights", flightService.getAllFlights());
        model.addAttribute("airports", airportService.getAllAirports());
        return FLIGHTSPAGE;

    }

    @GetMapping
    public String findFlightById(@RequestParam Long id, Model model) {
        model.addAttribute("flight", flightService.getById(id));
        model.addAttribute("airports", airportService.getAllAirports());
        return FLIGHTSETTINGS;
    }


    @GetMapping(value = NEW_TICKET)
    public String findFlightSetPriceByDate(@RequestParam Long id, Model model) {
        model.addAttribute("flight", ((FlightServiceImpl) flightService).getFlightByIdWithUpdatedPrice(id));
        return BUY;
    }

    @GetMapping(value = RETURN)
    public String goToSearchPage() {
        return "redirect:" + FLIGHTS + FLIGHT;
    }


    public String addNewFlight(Model model) {
        model.addAttribute("airports", airportService.getAllAirports());
            return ADDFLIGHT;
    }

    @GetMapping(value = FLIGHT)
    public String searchForFlight(Model model) {
        model.addAttribute("flights", ((FlightServiceImpl) flightService).getAllFlightsWithUpdatedPrice());
        model.addAttribute("airports", airportService.getAllAirports());
        return SEARCHPAGE;
    }

    @GetMapping(value = SEARCH)
    public String findFlightByFromToDate(@RequestParam Map<String, String> params, Model model) {
        if (params.get("departureDateTo").equals(""))
            params.put(("departureDateTo"),params.get("departureDateFrom"));
        model.addAttribute("flights", ((FlightServiceImpl) flightService).getFilteredFlightsWithUpdatedPrice
                (params.get("departureAirport"), params.get("arrivalAirport"),
                        LocalDate.parse(params.get("departureDateFrom")).atStartOfDay(),
                        LocalDate.parse(params.get("departureDateTo")).atStartOfDay()));
        model.addAttribute("airports", airportService.getAllAirports());
        if (params.get("adminPage").equals("true")) {
            return FLIGHTSPAGE;
        }
        return SEARCHPAGE;

    }

    @PostMapping
    public String addNewFlight(@RequestParam Map<String, String> params, Model model) {
        model.addAttribute("flight",
                flightService.addNewFlight(Flight.builder()
                        .initialPrice(Long.valueOf(params.get("initialPrice")))
                        .plane(Plane.builder()
                                .id(Long.valueOf(params.get("planeId")))
                                .build())
                        .departureDate(LocalDateTime.parse(params.get("departureDate")))
                        .departureAirport(Airport.builder()
                                .code(params.get("departureAirport"))
                                .build())
                        .arrivalAirport(Airport.builder()
                                .code(params.get("arrivalAirport"))
                                .build())
                        .businessPrice(Long.valueOf(params.get("businessPrice")))
                        .luggagePrice(Long.valueOf(params.get("luggagePrice")))
                        .placePriorityPrice(Long.valueOf(params.get("placePriorityPrice")))
                        .arrivalDate(LocalDateTime.parse(params.get("arrivalDate"))).build()));
        return "redirect:" + FLIGHTS + ALL;
    }

    @PostMapping(value = UPDATE)
    public String updateFlight(@RequestParam Map<String, String> params, Model model) {
        model.addAttribute("flight",
                flightService.updateFlight(
                        Flight.builder()
                                .id(Long.valueOf(params.get("id")))
                                .initialPrice(Long.valueOf(params.get("initialPrice")))
                                .plane(Plane.builder()
                                        .id(Long.valueOf(params.get("planeId")))
                                        .build()
                                )
                                .departureDate(LocalDateTime.parse(params.get("departureDate")))
                                .arrivalDate(LocalDateTime.parse(params.get("arrivalDate")))
                                .departureAirport(Airport.builder()
                                        .code(params.get("departureAirport"))
                                        .build())
                                .arrivalAirport(Airport.builder()
                                        .code(params.get("arrivalAirport"))
                                        .build())
                                .businessPrice(Long.valueOf(params.get("businessPrice")))
                                .luggagePrice(Long.valueOf(params.get("luggagePrice")))
                                .placePriorityPrice(Long.valueOf(params.get("placePriorityPrice")))
                                .build()));
        return "redirect:" + FLIGHTS + ALL;
    }

    @PostMapping(value = DELETE)
    public String deleteFlight(@RequestParam Long id, Model model) {
        model.addAttribute("flight", flightService.deleteFlight(id));
        return "redirect:" + FLIGHTS + ALL;
    }

}
