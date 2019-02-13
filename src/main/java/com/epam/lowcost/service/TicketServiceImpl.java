package com.epam.lowcost.service;

import com.epam.lowcost.DAO.TicketDAO;
import com.epam.lowcost.model.Flight;
import com.epam.lowcost.model.Ticket;
import com.epam.lowcost.model.User;


import java.util.List;

public class TicketServiceImpl implements TicketService {
    private TicketDAO ticketDAO;
    private UserService userService;
    private FlightService flightService;


    public TicketServiceImpl(TicketDAO ticketDAO, UserService userService, FlightService flightService) {
        this.ticketDAO = ticketDAO;
        this.userService = userService;
        this.flightService = flightService;
    }

    @Override
    public List<Ticket> getAllUserTickets(long id) {
        return ticketDAO.getAllUserTickets(id);
    }

    @Override
    public List<Ticket> getAllTickets() { return ticketDAO.getAllTickets();}

    @Override
    public Ticket addTicket(Ticket ticket) {
        Flight flight = flightService.getFlightById(ticket.getFlight().getId());
        User user = userService.getById(ticket.getUser().getId());
        ticket.setFlight(flight);
        ticket.setUser(user);
        long price = countPrice(ticket.getFlight().getInitialPrice(), ticket);
        ticket.setPrice(price);
        return ticketDAO.addTicket(ticket);
    }

    @Override
    public Ticket updateTicket(Ticket ticket) {
        Ticket beforeUpdateTicket = ticketDAO.getById(ticket.getId());
        if(beforeUpdateTicket == null)
            return null;
        Flight flight = flightService.getFlightById(beforeUpdateTicket.getFlight().getId());
        User user = userService.getById(beforeUpdateTicket.getUser().getId());
        ticket.setFlight(flight);
        ticket.setUser(user);
        long price = countPrice(ticket.getFlight().getInitialPrice(), ticket);
        ticket.setPrice(price);
        return ticketDAO.updateTicket(ticket);
    }

    @Override
    public Ticket deleteTicket(long id) {
        return ticketDAO.deleteTicket(id);
    }

    private long countPrice(long initialPrice, Ticket ticket){
        if(ticket.isBusiness())
            initialPrice+=5000;
        if(ticket.isPlacePriority())
            initialPrice+=500;
        if(ticket.isHasLuggage()){
            initialPrice+=900;
        }
        return initialPrice;
    }
}
