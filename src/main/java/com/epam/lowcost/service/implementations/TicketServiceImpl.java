package com.epam.lowcost.service.implementations;

import com.epam.lowcost.DAO.interfaces.TicketDAO;
import com.epam.lowcost.model.Flight;
import com.epam.lowcost.model.Ticket;
import com.epam.lowcost.model.User;
import com.epam.lowcost.service.interfaces.FlightService;
import com.epam.lowcost.service.interfaces.TicketService;
import com.epam.lowcost.service.interfaces.UserService;

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
    public List<Ticket> getAllTickets() {
        return ticketDAO.getAllTickets();
    }

    @Override
    public Ticket addTicket(Ticket ticket) {
        Flight flight = flightService.getById(ticket.getFlight().getId());
        User user = userService.getById(ticket.getUser().getId());
        ticket.setFlight(flight);
        ticket.setUser(user);
        ticket.setPrice(ticket.getFlight().getInitialPrice());
        return ticketDAO.addTicket(ticket);
    }

    @Override
    public Ticket updateTicket(Ticket ticket) {
        Ticket beforeUpdateTicket = ticketDAO.getById(ticket.getId());
        if (beforeUpdateTicket == null)
            return null;
        Flight flight = flightService.getById(beforeUpdateTicket.getFlight().getId());
        User user = userService.getById(beforeUpdateTicket.getUser().getId());
        ticket.setFlight(flight);
        ticket.setUser(user);
        ticket.setPrice(ticket.getFlight().getInitialPrice());
        return ticketDAO.updateTicket(ticket);
    }

    @Override
    public Ticket deleteTicket(long id) {
        return ticketDAO.deleteTicket(id);
    }

    @Override
    public int deleteTicketsByFlightId(long id) {return ticketDAO.deleteTicketsByFlightId(id);}


}
