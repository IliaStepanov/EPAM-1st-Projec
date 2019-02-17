package com.epam.lowcost.controller;

import com.epam.lowcost.model.Flight;
import com.epam.lowcost.model.Plane;
import com.epam.lowcost.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;


@Controller
@RequestMapping(value = "/flights")
public class FlightController {
    @Autowired
    FlightService flightService;

    @GetMapping(value = "/all")
    public String getAllFlights(Model model) {
        model.addAttribute("flights", flightService.getAllFlights());
        return "search";
    }

    @GetMapping
    public String findFlightById(@RequestParam Long id, Model model) {
        model.addAttribute("flight", flightService.getById(id));
        return "flights";
    }



    @GetMapping(value = "/search")
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
                        .arrivalDate(LocalDateTime.parse(params.get("arrivalDate"))).build()));
        return "flights";
    }

    @PostMapping(value = "/update")
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
                                .arrivalAirport(params.get("arrivalAirport"))
                                .build()));
        return "flights";
    }

    @PostMapping(value = "/delete")
    public String deleteFlight(@RequestParam Long id, Model model) {
        model.addAttribute("flight", flightService.deleteFlight(id));
        return "flights";
    }

}
