package com.epam.lowcost.controller;

import com.epam.lowcost.model.Flight;
import com.epam.lowcost.model.Ticket;
import com.epam.lowcost.model.User;
import com.epam.lowcost.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.Map;


@Controller
@RequestMapping(value = "/tickets")
public class TicketController {

    @Autowired
    TicketService ticketService;


    @GetMapping(value = "/all")
    public String getAllUsers(Model model) {
        model.addAttribute("tickets", ticketService.getAllTickets());
        return "tickets";
    }

    @GetMapping
    public String getById(@RequestParam long id, Model model) {
        model.addAttribute("tickets", ticketService.getAllUserTickets(id));
        return "tickets";
    }

    @PostMapping
    public String addTicket(@RequestParam Map<String, String> params, Model model) {
        User user = new User();
        user.setId(Long.parseLong(params.get("userId")));
        Flight flight = new Flight();
        flight.setId(Long.parseLong(params.get("flightId")));

        model.addAttribute("ticket", ticketService.addTicket(
                Ticket.builder()
                        .user(user)
                        .flight(flight)
                        .hasLuggage(Boolean.parseBoolean(params.get("hasLuggage")))
                        .placePriority(Boolean.parseBoolean(params.get("placePriority")))
                        .isBusiness(Boolean.parseBoolean(params.get("isBusiness")))
                        .build()));

        model.addAttribute("message", "Ticket successfully added");
        return "tickets";
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
        return "tickets";
    }

    @PostMapping(value = "/delete")
    public String deleteTicket(@RequestParam long id, Model model) {
        model.addAttribute("message", ticketService.deleteTicket(id));
        return "tickets";
    }


}
