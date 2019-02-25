package com.epam.lowcost.controller;

import com.epam.lowcost.model.Airport;
import com.epam.lowcost.model.User;
import com.epam.lowcost.service.interfaces.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static com.epam.lowcost.util.Constants.DEFAULT_NUMBER_OF_AIRPORTS_ON_PAGE;
import static com.epam.lowcost.util.EndPoints.*;

@Controller
@RequestMapping(value = AIRPORT)
@SessionAttributes({"sessionUser", "number"})
public class AirportController {
    AirportService airportService;

    @Autowired
    public AirportController(AirportService airportService) {
        this.airportService = airportService;
    }

    @GetMapping(value = ALL + "/{pageId}")
    public String getAllAirports(@PathVariable int pageId, @ModelAttribute(value = "sessionUser") User sessionUser, ModelMap model) {
        if (!sessionUser.isAdmin()) {
            return "redirect:" + TICKETS + SELF;
        }

        int numberOfUsersOnPage = (int) model.getOrDefault("number", DEFAULT_NUMBER_OF_AIRPORTS_ON_PAGE);

        Map<String, Object> pageRepresentation = airportService.getAirportsByPage(pageId, numberOfUsersOnPage);

        model.addAttribute("pagesNum", pageRepresentation.get("pagesNum"));
        model.addAttribute("airports", pageRepresentation.get("airports"));
        model.addAttribute("pageId", pageRepresentation.get("pageId"));
        return AIRPORTSPAGE;
    }

    @GetMapping(value = PAGE)
    public String setUsersByPage(@RequestParam String number, @RequestParam String fromPage, Model model) {
        model.addAttribute("number", Integer.parseInt(number));
        return "redirect:" + fromPage + FIRST_PAGE;
    }

    @GetMapping
    public String getAirportByCode(@RequestParam String code, Model model) {
        model.addAttribute("airport", airportService.getAirportByCode(code.toUpperCase()));
        return AIRPORTSPAGE;
    }

    @PostMapping(value = ADD)
    public String addAirport(@RequestParam Map<String, String> params, Model model) {
        model.addAttribute("airport", airportService.addNewAirport(params));
        model.addAttribute("message", "Airport successfully added");
        return AIRPORTSPAGE;
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
        return AIRPORTSPAGE;
    }


}
