package com.epam.lowcost.DAO.implementations;


import com.epam.lowcost.DAO.interfaces.TicketDAO;
import com.epam.lowcost.model.Ticket;
import com.epam.lowcost.util.DateFormatter;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
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
                "INSERT INTO TICKETS (userId, flightId, isBusiness, hasLuggage, placePriority, price, purchaseDate, isDeleted) " +
                        "VALUES ('%d', '%d', '%b', '%b', '%b', '%d', '%s', false)",
                ticket.getUser().getId(), ticket.getFlight().getId(), ticket.isBusiness(), ticket.isHasLuggage(),
                ticket.isPlacePriority(), ticket.getPrice(), DateFormatter.format(LocalDateTime.now()));

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
