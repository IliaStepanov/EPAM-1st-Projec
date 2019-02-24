package com.epam.lowcost.controller;

import com.epam.lowcost.model.Airport;
import com.epam.lowcost.service.interfaces.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

import static com.epam.lowcost.util.EndPoints.*;

@Controller
@RequestMapping(value = AIRPORT)
public class AirportController {
    AirportService airportService;

    @Autowired
    public AirportController(AirportService airportService) {
        this.airportService = airportService;
    }

    @GetMapping(value = ALL)
    public String getAllAirports(Model model) {
        model.addAttribute("airports", airportService.getAllAirports());
        return "airports";
    }

    @GetMapping
    public String getAirportByCode(@RequestParam String code, Model model) {
        model.addAttribute("airport", airportService.getAirportByCode(code.toUpperCase()));
        return "airports";
    }

    @PostMapping(value = ADD)
    public String addAirport(@RequestParam Map<String, String> params, Model model) {
        model.addAttribute("airport", airportService.addNewAirport(params));
        model.addAttribute("message", "Airport successfully added");
        return "airports";
    }

    @PostMapping(value = UPDATE)
    public String updateAirport(@RequestParam Map<String, String> params, Model model) {
        Airport airport = airportService.updateAirport(params);
        if (airport == null) {
            model.addAttribute("message", "No such airport or it has been deleted!");
        } else {
            model.addAttribute("airport", airport);
            model.addAttribute("message", "Airport successfully added");
        }
        return "airports";
    }


}
