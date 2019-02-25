package com.epam.lowcost.service.implementations;

import com.epam.lowcost.DAO.implementations.TicketDAOImpl;
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


    public TicketServiceImpl(TicketDAO ticketDAO) {
        this.ticketDAO = ticketDAO;
    }

    public void setFlightService(FlightService flightService){
        this.flightService = flightService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
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
        Flight flight = ((FlightServiceImpl) flightService).getFlightByIdWithUpdatedPrice(ticket.getFlight().getId());
        User user = userService.getById(ticket.getUser().getId());
        ticket.setFlight(flight);
        ticket.setUser(user);
        ticket.setPrice(flight.getInitialPrice());
        ticket.setPrice(countPrice(ticket));
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
        ticket.setPrice(flight.getInitialPrice());
        ticket.setUser(user);
        ticket.setPrice(countPrice(ticket));
        return ticketDAO.updateTicket(ticket);
    }

    @Override
    public String deleteTicket(long id) {
        return ticketDAO.deleteTicket(id);
    }

    @Override
    public int numberBoughtPlaces(long flightId, boolean isBusiness) {
        return ticketDAO.numberBoughtPlaces(flightId, isBusiness);
    }

    @Override
    public boolean deleteTicketsByFlightId(long id) {
        return ticketDAO.deleteTicketsByFlightId(id);
    }

    @Override
    public boolean deleteTicketsByUserId(long id) {
        return ticketDAO.deleteTicketsByUserId(id);
    }

    private long countPrice(Ticket ticket) {
        long price = ticket.getPrice();
        Flight flight = ticket.getFlight();
        if (ticket.isHasLuggage()) {
            price += flight.getLuggagePrice();
        }
        if (ticket.isPlacePriority()) {
            price += flight.getPlacePriorityPrice();
        }
        if (ticket.isBusiness()) {
            price += flight.getBusinessPrice();
        }
        return price;

    }


}
