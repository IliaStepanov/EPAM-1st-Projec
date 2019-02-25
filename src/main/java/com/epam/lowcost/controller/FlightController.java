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
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Map;

import static com.epam.lowcost.util.Constants.DEFAULT_NUMBER_OF_FLIGHTS_ON_PAGE;
import static com.epam.lowcost.util.EndPoints.*;


@Controller
@RequestMapping(value = FLIGHTS)
@SessionAttributes(value = "number")
public class FlightController {

   private FlightService flightService;
   private AirportService airportService;

    @Autowired
    public FlightController(FlightService flightService, AirportService airportService) {

        this.flightService = flightService;
        this.airportService = airportService;
    }

    @GetMapping(value = ALL + "/{pageId}")
    public String getAllFlights(@PathVariable int pageId, ModelMap model) {

        int numberOfFlightsOnPage = (int) model.getOrDefault("number", DEFAULT_NUMBER_OF_FLIGHTS_ON_PAGE);

        Map<String, Object> pageRepresentation = flightService.getFlightsByPage(pageId, numberOfFlightsOnPage);

        model.addAttribute("pagesNum", pageRepresentation.get("pagesNum"));
        model.addAttribute("flights", pageRepresentation.get("flights"));
        model.addAttribute("pageId", pageRepresentation.get("pageId"));
        model.addAttribute("airports", airportService.getAllAirports());
        return FLIGHTSPAGE;

    }

    @GetMapping(value = PAGE)
    public String setFlightsByPage(@RequestParam String number,@RequestParam String fromPage, ModelMap model) {
        model.addAttribute("number", Integer.parseInt(number));

        return "redirect:" + fromPage + FIRST_PAGE;
    }

    @GetMapping
    public String findFlightById(@RequestParam Long id, Model model) {
        model.addAttribute("flight", flightService.getById(id));
        model.addAttribute("airports", airportService.getAllAirports());
        return FLIGHTSETTINGS;
    }


    @GetMapping(value = NEW_TICKET)
    public String findFlightSetPriceByDate(@RequestParam Long id, Model model) {
        model.addAttribute("flight", flightService.getFlightByIdWithUpdatedPrice(id));
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

    @GetMapping(value = FLIGHT + "/{pageId}")
    public String searchForFlight(@PathVariable int pageId, ModelMap model) {

        int numberOfFlightsOnPage = (int) model.getOrDefault("number", DEFAULT_NUMBER_OF_FLIGHTS_ON_PAGE);

        Map<String, Object> pageRepresentation = flightService.getAllFlightsWithUpdatedPrice(pageId, numberOfFlightsOnPage);

        model.addAttribute("pagesNum", pageRepresentation.get("pagesNum"));
        model.addAttribute("flights", pageRepresentation.get("flights"));
        model.addAttribute("pageId", pageRepresentation.get("pageId"));
        model.addAttribute("airports", airportService.getAllAirports());
        return SEARCHPAGE;
    }

    @GetMapping(value = SEARCH)
    public String findFlightByFromToDate(@RequestParam Map<String, String> params, Model model) {
        if (params.get("departureDateTo").equals(""))
            params.put(("departureDateTo"),params.get("departureDateFrom"));
        model.addAttribute("flights",  flightService.getFilteredFlightsWithUpdatedPrice
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
                        .departureDate(LocalDate.parse(params.get("departureDate")).atStartOfDay())
                        .arrivalDate(LocalDate.parse(params.get("arrivalDate")).atStartOfDay())
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
                                .departureDate(LocalDate.parse(params.get("departureDate")).atStartOfDay())
                                .arrivalDate(LocalDate.parse(params.get("arrivalDate")).atStartOfDay())
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
