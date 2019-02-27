package com.epam.lowcost.controller;

import com.epam.lowcost.model.Flight;
import com.epam.lowcost.model.Ticket;
import com.epam.lowcost.model.User;
import com.epam.lowcost.service.interfaces.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static com.epam.lowcost.util.Constants.DEFAULT_NUMBER_OF_TICKETS_ON_PAGE;
import static com.epam.lowcost.util.EndPoints.*;


@Controller
@RequestMapping(value = TICKETS)
@SessionAttributes({"sessionUser", "number"})
public class TicketController {

    @Autowired
    TicketService ticketService;


    @GetMapping(value = ALL + "/{pageId}")
    public String getAllTickets(@PathVariable int pageId,  ModelMap model) {
       int numberOfTicketsOnPage = (int) model.getOrDefault("number", DEFAULT_NUMBER_OF_TICKETS_ON_PAGE);

        Map<String, Object> pageRepresentation = ticketService.getTicketsByPage(pageId, numberOfTicketsOnPage);

        model.addAttribute("pagesNum", pageRepresentation.get("pagesNum"));
        model.addAttribute("tickets", pageRepresentation.get("tickets"));
        model.addAttribute("pageId", pageRepresentation.get("pageId"));
        return TICKETSPAGE;
    }

    @GetMapping(value = PAGE)
    public String setUsersByPage(@RequestParam String number, @RequestParam String fromPage, Model model) {
        model.addAttribute("number", Integer.parseInt(number));
        return "redirect:" + fromPage + FIRST_PAGE;
    }

    @GetMapping
    public String getById(@RequestParam long id, Model model) {
        model.addAttribute("tickets", ticketService.getAllUserTickets(id));
        return TICKETSPAGE;
    }

    @PostMapping(value = ADD)
    public String addTicket(@ModelAttribute("sessionUser") User sessionUser,
                            @RequestParam Map<String, String> params, Model model) {
        Flight flight = Flight.builder()
                .id(Long.parseLong(params.get("flightId")))
                .build();

        model.addAttribute("ticket", ticketService.addTicket(
                Ticket.builder()
                        .user(sessionUser)
                        .flight(flight)
                        .hasLuggage(Boolean.parseBoolean(params.get("hasLuggage")))
                        .placePriority(Boolean.parseBoolean(params.get("placePriority")))
                        .isBusiness(Boolean.parseBoolean(params.get("isBusiness")))
                        .build()));

        model.addAttribute("message", "Ticket successfully added");
        return "redirect:" + TICKETS + SELF;
    }

    @PostMapping(value = UPDATE)
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
        return "redirect:" + TICKETS + SELF;
    }

    @PostMapping(value = DELETE)
    public String deleteTicket(@RequestParam long id, Model model) {
        model.addAttribute("message", ticketService.deleteTicket(id));
        return "redirect:" + TICKETS + ALL;
    }

    @PostMapping(value = CANCEL)
    public String cancelTicket(@RequestParam long id, Model model) {
        model.addAttribute("message", ticketService.deleteTicket(id));
        return "redirect:" + TICKETS + SELF;
    }


    @GetMapping(value = SELF)
    public String getAllUserTickets(@ModelAttribute("sessionUser") User sessionUser, Model model) {
        model.addAttribute("currentUserTickets", ticketService.getAllUserTickets(sessionUser.getId()));
        return USERPAGE;
    }


}
