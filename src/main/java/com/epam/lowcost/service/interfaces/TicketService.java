package com.epam.lowcost.service.interfaces;

import com.epam.lowcost.model.Ticket;

import java.util.List;
import java.util.Map;

public interface TicketService {
    List<Ticket> getAllUserTickets(long userId);

    List<Ticket> getAllTickets();

    int numberBoughtPlaces(long flightId, boolean isBusiness);

    boolean deleteTicketsByFlightId(long id);

    boolean deleteTicketsByUserId(long id);

    Ticket addTicket(Ticket ticket);

    Ticket updateTicket(Ticket ticket);

    String deleteTicket(long id);


    Map<String,Object> getTicketsByPage(int pageId, int numberOfTicketsOnPage);

    int countTickets();

}
