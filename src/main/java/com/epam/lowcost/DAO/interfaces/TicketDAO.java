package com.epam.lowcost.DAO.interfaces;

import com.epam.lowcost.model.Ticket;

import java.util.List;

public interface TicketDAO {
    List<Ticket> getAllTickets();

    List<Ticket> getAllUserTickets(long userId);

    Ticket getById(long currentId);

    Ticket addTicket(Ticket ticket);

    Ticket updateTicket(Ticket ticket);

    boolean deleteTicketsByFlightId(long id);

    boolean deleteTicketsByUserId(long id);

    String deleteTicket(long id);

    List<Ticket> getTicketsByPage(int pageId, int ticketsByPage);

    int countTickets();

}
