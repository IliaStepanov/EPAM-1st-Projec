package com.epam.lowcost.controller;

import com.epam.lowcost.model.Flight;
import com.epam.lowcost.model.Ticket;
import com.epam.lowcost.model.User;
import com.epam.lowcost.service.interfaces.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@Controller
@RequestMapping(value = "/tickets")
@SessionAttributes(value = "sessionUser")
public class TicketController {

    @Autowired
    TicketService ticketService;


    @GetMapping(value = "/all")
    public String getAllTickets(Model model) {
        model.addAttribute("tickets", ticketService.getAllTickets());
        return "tickets";
    }

    @GetMapping
    public String getById(@RequestParam long id, Model model) {
        model.addAttribute("tickets", ticketService.getAllUserTickets(id));
        return "tickets";
    }

    @PostMapping(value = "/add")
    public String addTicket(@ModelAttribute("sessionUser") User sessionUser,
                            @RequestParam Map<String, String> params, Model model) {
        User user = new User();
        user.setId(sessionUser.getId());
        Flight flight = new Flight();
        flight.setId(Long.parseLong(params.get("flightId")));

        model.addAttribute("ticket", ticketService.addTicket(
                Ticket.builder()
                        .user(sessionUser)
                        .flight(flight)
                        .hasLuggage(Boolean.parseBoolean(params.get("hasLuggage")))
                        .placePriority(Boolean.parseBoolean(params.get("placePriority")))
                        .isBusiness(Boolean.parseBoolean(params.get("isBusiness")))
                        .build()));

        model.addAttribute("message", "Ticket successfully added");
        return "buy";
    }

    @PostMapping(value = "/update")
    public String updateTicket(@RequestParam Map<String, String> params, Model model) {
        Ticket ticket = ticketService.updateTicket(
                Ticket.builder()
                        .id(Long.parseLong(params.get("ticketId")))
                        .hasLuggage(Boolean.parseBoolean(params.get("hasLuggage")))
                        .placePriority(Boolean.parseBoolean(params.get("placePriority")))
                        .isBusiness(Boolean.parseBoolean(params.get("isBusiness")))
                        .build());

        if (ticket == null) {
            model.addAttribute("message", "No such ticket or it has been deleted!");
        } else {
            model.addAttribute("ticket", ticket);
            model.addAttribute("message", "Ticket successfully updated");
        }
        return "redirect:/tickets/self";
    }

    @PostMapping(value = "/delete")
    public String deleteTicket(@RequestParam long id, Model model) {
        model.addAttribute("message", ticketService.deleteTicket(id));
        return "tickets";
    }


    @GetMapping(value = "/self")
    public String getAllUserTickets(@ModelAttribute("sessionUser") User sessionUser, Model model) {
        model.addAttribute("currentUserTickets", ticketService.getAllUserTickets(sessionUser.getId()));
        return "userPage";
    }


}
