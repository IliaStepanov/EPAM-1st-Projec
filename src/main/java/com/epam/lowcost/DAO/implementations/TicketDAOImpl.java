package com.epam.lowcost.DAO.implementations;


import com.epam.lowcost.DAO.interfaces.TicketDAO;
import com.epam.lowcost.exceptions.DatabaseErrorException;
import com.epam.lowcost.model.Ticket;
import com.epam.lowcost.util.DateFormatter;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.util.List;

public class TicketDAOImpl extends AbstractDAOImpl<Ticket> implements TicketDAO {

    public TicketDAOImpl(DataSource dataSource, RowMapper<Ticket> rowMapper) {
        super(dataSource, rowMapper);
    }

    @Override
    public List<Ticket> getAllUserTickets(long currentUserId) throws DatabaseErrorException {


        String sql = String.format(joinQuery() + "and TICKETS.userId=%d", currentUserId);
        return executeSqlSelect(sql);

    }

    @Override
    public List<Ticket> getAllTickets() throws DatabaseErrorException {
        return executeSqlSelect(joinQuery());
    }

    @Override
    public Ticket addTicket(Ticket ticket) throws DatabaseErrorException {
        String sql = String.format(
                "INSERT INTO TICKETS (userId, flightId, isBusiness, hasLuggage, placePriority, price, purchaseDate, isDeleted) " +
                        "VALUES ('%d', '%d', '%b', '%b', '%b', '%d', '%s', false)",
                ticket.getUser().getId(), ticket.getFlight().getId(), ticket.isBusiness(), ticket.isHasLuggage(),
                ticket.isPlacePriority(), ticket.getPrice(), DateFormatter.format(LocalDateTime.now()));
        return executeSqlInsert(ticket, sql);
    }

    @Override
    public Ticket getById(long currentId) throws DatabaseErrorException {
        String sql = String.format(joinQuery() + "and TICKETS.id=%d", currentId);
        return executeSqlSelect(sql).get(0);
    }

    @Override
    public Ticket updateTicket(Ticket ticket) {

        String sql = String.format("UPDATE TICKETS SET hasLuggage='%b',placePriority='%b'," +
                        "isBusiness='%b', price='%d' WHERE id = %d",
                ticket.isHasLuggage(),
                ticket.isPlacePriority(),
                ticket.isBusiness(),
                ticket.getPrice(),
                ticket.getId());
        return executeSqlUpdate(sql) == 1 ? ticket : null;
    }

    @Override
    public String deleteTicket(long id) {
        String sql = String.format("UPDATE TICKETS SET ISDELETED=TRUE WHERE ID=%d", id);
        return executeSqlUpdate(sql) == 1 ? String.format("Ticket %d successfully deleted", id)
                : "Ticket was not deleted";
    }

     public boolean deleteTicketsByFlightId(long id) {
        try {
            JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

            String sql = "UPDATE TICKETS SET isDeleted =TRUE WHERE TICKETS.flightId = ?";
            jdbcTemplate.update(sql, id);
            return true;

        } catch (EmptyResultDataAccessException e) {
            return false;
        }
    }

    public boolean deleteTicketsByUserId(long id) {
      try {
            JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
            String sql = "UPDATE TICKETS SET isDeleted =TRUE WHERE TICKETS.userId = ?";
            jdbcTemplate.update(sql, id);
            return true;
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
       
    }

    public int countBusinessPlaces(long id) {
        int n;
        try {
            JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
            List<Ticket> tickets = jdbcTemplate.query(joinQuery() + "and flightId=? and isBusiness=?",
                    rowMapper, id, true);
            n = tickets.size();
        } catch (EmptyResultDataAccessException e) {
            return 0;
        }
        return n;
    }

    public int countEconomPlaces(long id) {
        int n;
        try {
            JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
            List<Ticket> tickets = jdbcTemplate.query(joinQuery() + "and flightId=? and isBusiness=?",
                    rowMapper, id, false);
            n = tickets.size();
        } catch (EmptyResultDataAccessException e) {
            return 0;
        }
        return n;
    }


    private String joinQuery() {
        return "SELECT * FROM TICKETS " +
                "JOIN  USERS ON TICKETS.userId=USERS.id " +
                "JOIN  FLIGHTS ON TICKETS.flightId=FLIGHTS.id " +
                "JOIN  PLANES ON FLIGHTS.planeId = PLANES.id " +
                "JOIN AIRPORTS AS a " +
                "ON FLIGHTS.DEPARTUREAIRPORT=a.code JOIN AIRPORTS AS b " +
                "ON FLIGHTS.ARRIVALAIRPORT=b.code " +
                "WHERE TICKETS.isDeleted=false ";
    }

}
