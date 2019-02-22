package com.epam.lowcost.service.interfaces;

import com.epam.lowcost.model.Ticket;

import java.util.List;

public interface TicketService {
    List<Ticket> getAllUserTickets(long userId);

    List<Ticket> getAllTickets();

    boolean deleteTicketsByFlightId(long id);

    boolean deleteTicketsByUserId(long id);

    Ticket addTicket(Ticket ticket);

    Ticket updateTicket(Ticket ticket);


    String deleteTicket(long id);

}
