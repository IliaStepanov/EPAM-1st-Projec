package com.epam.lowcost.DAO;

import com.epam.lowcost.model.Ticket;

import java.util.List;

public interface TicketDAO {
    List<Ticket> getAllTickets();
    List<Ticket> getAllUserTickets(long userId);
    Ticket getById(long currentId);
    Ticket addTicket(Ticket ticket);
    Ticket updateTicket(Ticket ticket);
    Ticket deleteTicket(long id);
}
