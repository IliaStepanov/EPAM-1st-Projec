package com.epam.lowcost.DAO.implementations;


import com.epam.lowcost.DAO.interfaces.TicketDAO;
import com.epam.lowcost.model.Ticket;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TicketDAOImpl implements TicketDAO {

    private DataSource dataSource;
    private RowMapper<Ticket> ticketRowMapper;

    public TicketDAOImpl(DataSource dataSource, RowMapper<Ticket> ticketRowMapper) {
        this.dataSource = dataSource;
        this.ticketRowMapper = ticketRowMapper;
    }

    @Override
    public List<Ticket> getAllUserTickets(long currentUserId) {
        List<Ticket> allTickets = new ArrayList<>();

        String sql = String.format(joinQuery() + "and TICKETS.userId=%d", currentUserId);

        try (Connection conn = dataSource.getConnection();
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery(sql)) {
            while (rs.next()) {
                allTickets.add(ticketRowMapper.mapRow(rs, 1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allTickets;
    }

    @Override
    public List<Ticket> getAllTickets() {
        List<Ticket> allTickets = new ArrayList<>();
        String sql = joinQuery();
        try (Connection conn = dataSource.getConnection();
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery(sql)) {
            while (rs.next()) {
                allTickets.add(ticketRowMapper.mapRow(rs, 1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allTickets;
    }

    @Override
    public Ticket addTicket(Ticket ticket) {
        String sql = String.format(
                "INSERT INTO TICKETS (userId, flightId, isBusiness, hasLuggage,placePriority, price, isDeleted) " +
                        "VALUES ('%d', '%d', '%b', '%b', '%b', '%d', false)",
                ticket.getUser().getId(), ticket.getFlight().getId(), ticket.isBusiness(), ticket.isHasLuggage(),
                ticket.isPlacePriority(), ticket.getPrice());

        try (Connection connection = dataSource.getConnection();
             Statement stm = connection.createStatement()
        ) {
            int insert = stm.executeUpdate(sql);
            if (insert == 1) {
                ResultSet rs = stm.executeQuery("SELECT * FROM TICKETS");
                rs.last();
                long newId = rs.getLong("id");
                ticket.setId(newId);
                return ticket;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        ticket = null;
        return ticket;
    }

    @Override
    public Ticket getById(long currentId) {
        Ticket ticket = null;
        String sql = String.format(joinQuery() + "and TICKETS.id=%d", currentId);
        try (Connection conn = dataSource.getConnection();
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery(sql)) {
            while (rs.next())
                ticket = ticketRowMapper.mapRow(rs, 1);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ticket;
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

        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement()) {
            int lines = stmt.executeUpdate(sql);
            if (lines == 1)
                return ticket;
        } catch (SQLException e) {
            e.printStackTrace();

        }
        ticket = null;
        return ticket;
    }

    @Override
    public Ticket deleteTicket(long id) {
        Ticket ticket = getById(id);
        if (ticket == null)
            return null;
        ticket.setDeleted(true);
        String sql = String.format("UPDATE TICKETS SET isDeleted = TRUE WHERE id = '%d'", id);
        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement()) {
            int lines = stmt.executeUpdate(sql);
            if (lines == 1) {
                return ticket;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ticket = null;
        return ticket;
    }

    public boolean deleteTicketsByFlightId(long id) {
        int required = 0, updated = 0;
        try {
            JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
            String query = "Select * FROM Tickets JOIN  USERS ON TICKETS.userId=USERS.id " +
                    "JOIN  FLIGHTS ON TICKETS.flightId=FLIGHTS.id " +
                    "JOIN  PLANES ON FLIGHTS.planeId = PLANES.id " +
                    "WHERE TICKETS.isDeleted=false AND TICKETS.FLIGHTID = ?";
            List<Ticket> tickets = jdbcTemplate.query(query, ticketRowMapper, id);
            required = tickets.size();
            String sql = "UPDATE TICKETS SET isDeleted =TRUE WHERE TICKETS.flightId = ?";
            updated = jdbcTemplate.update(sql, id);
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
        return required == updated;
    }

    public boolean deleteTicketsByUserId(long id) {
        int required = 0, updated = 0;
        try {
            JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
            String query = "Select * FROM Tickets JOIN  USERS ON TICKETS.userId=USERS.id " +
                    "JOIN  FLIGHTS ON TICKETS.flightId=FLIGHTS.id " +
                    "JOIN  PLANES ON FLIGHTS.planeId = PLANES.id " +
                    "WHERE TICKETS.isDeleted=false AND userId = ?";
            List<Ticket> tickets = jdbcTemplate.query(query, ticketRowMapper, id);
            required = tickets.size();
            String sql = "UPDATE TICKETS SET isDeleted =TRUE WHERE TICKETS.userId = ?";
            updated = jdbcTemplate.update(sql, id);

        } catch (EmptyResultDataAccessException e) {
            return false;
        }
        return required == updated;
    }


    public int countBusinessPlaces(long id) {
        int n;
        try {
            JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
            List<Ticket> tickets = jdbcTemplate.query(joinQuery() + "and flightId=? and isBusiness=?",
                    ticketRowMapper, id, true);
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
                    ticketRowMapper, id, false);
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
                "WHERE TICKETS.isDeleted=false ";
    }

}
