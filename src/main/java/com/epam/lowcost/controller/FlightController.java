package com.epam.lowcost.controller;

import com.epam.lowcost.model.Flight;
import com.epam.lowcost.service.FlightService;
import com.epam.lowcost.service.PlaneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;


@Controller
@RequestMapping(value = "/flight")
public class FlightController {
    @Autowired
    FlightService flightService;

    @Autowired
    PlaneService planeService;

    @GetMapping(value = "/getAllFlights")
    public String getAllFlights(Model model) {
        model.addAttribute("flights", flightService.getAllFlights());
        return "flight";
    }

    @GetMapping
    public String findFlightById(@RequestParam Long id, Model model) {
        model.addAttribute("flight", flightService.getFlightById(id));
        return "flight";
    }

    @PostMapping
    public String addNewFlight(@RequestParam Map<String, String> params, Model model) {
        System.out.println("here");
        System.out.println(Long.valueOf(params.get("plane_id")));
        System.out.println(planeService);
        System.out.println("here");
        model.addAttribute("flight",
                flightService.addNewFlight(Flight.builder().initialPrice(Long.valueOf(params.get("initialPrice"))).
                        plane(planeService.getPlaneById(Long.valueOf(params.get("plane_id")))).
                        departureDate(LocalDateTime.parse(params.get("departureDate"))).
                        arrivalDate(LocalDateTime.parse(params.get("arrivalDate"))).build()));
        return "flight";
    }

    @PostMapping(value = "/updateFlight")
    public String updateFlight(@RequestParam Map<String, String> params, Model model) {
        model.addAttribute("flight",
                flightService.updateFlight(Flight.builder().id(Long.valueOf(params.get("id"))).
                        initialPrice(Long.valueOf(params.get("initialPrice"))).
                        plane(planeService.getPlaneById(Long.valueOf(params.get("plane_id")))).
                        departureDate(LocalDateTime.parse(params.get("departureDate"))).
                        arrivalDate(LocalDateTime.parse(params.get("arrivalDate"))).build()));
        return "flight";
    }

    @PostMapping (value = "/deleteFlight")
    public String deleteFlight(@RequestParam Long id, Model model) {
        model.addAttribute("flight", flightService.deleteFlight(id));
        return "flight";
    }

}
