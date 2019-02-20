package com.epam.lowcost.controller;

import com.epam.lowcost.model.Flight;
import com.epam.lowcost.model.Plane;
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
    @Autowired
    FlightService flightService;

    @GetMapping(value = ALL)
    public String getAllFlights(Model model) {
        model.addAttribute("flights", flightService.getAllFlights());
        return "search";
    }

    @GetMapping
    public String findFlightById(@RequestParam Long id, Model model) {
        model.addAttribute("flight", flightService.getById(id));
        return "flightSettings";
    }

    @GetMapping(value = "/new-ticket")
    public String findFlightSetPriceByDate(@RequestParam Long id, Model model) {
        model.addAttribute("flight", flightService.getById(id));
        return "buy";
    }

    @GetMapping(value = "/return")
    public String goToSearchPage() {
        return "redirect:/flights/all";
    }


    @GetMapping(value = "add")
    public String addNewFlight() {
        return "flights";
    }


    @GetMapping(value = SEARCH)

    public String findFlightByFromToDate(@RequestParam Map<String, String> params, Model model) {
        model.addAttribute("flights", flightService.getByFromToDate
                (params.get("departureAirport"), params.get("arrivalAirport"),
                        LocalDate.parse(params.get("departureDateFrom")).atStartOfDay(),
                        LocalDate.parse(params.get("departureDateTo")).atStartOfDay()));
        return "search";
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
                        .departureAirport(params.get("departureAirport"))
                        .arrivalAirport(params.get("arrivalAirport"))
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
                                .departureAirport(params.get("departureAirport"))
                                .businessPrice(Long.valueOf(params.get("businessPrice")))
                                .luggagePrice(Long.valueOf(params.get("luggagePrice")))
                                .placePriorityPrice(Long.valueOf(params.get("placePriorityPrice")))
                                .arrivalAirport(params.get("arrivalAirport"))
                                .build()));
        return "redirect:" + FLIGHTS + ALL;
    }

    @PostMapping(value = DELETE)
    public String deleteFlight(@RequestParam Long id, Model model) {
        model.addAttribute("flight", flightService.deleteFlight(id));
        return "redirect:" + FLIGHTS + ALL;
    }

}
