package com.epam.lowcost.DAO;


import com.epam.lowcost.model.Ticket;
import com.epam.lowcost.util.FlightRowMapper;
import com.epam.lowcost.util.UserRowMapper;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TicketDAOImpl implements TicketDAO{

    private DataSource dataSource;

    public TicketDAOImpl(DataSource dataSource) { this.dataSource = dataSource;}

    @Override
    public List <Ticket> getAllUserTickets(long currentUserId) {
        List<Ticket> allTickets = new ArrayList<>();
        Ticket ticket;
        String sql = String.format("SELECT * FROM TICKETS " +
                        "JOIN  USERS ON TICKETS.userId=USERS.id " +
                        "JOIN  FLIGHTS ON TICKETS.flightId=FLIGHTS.id " +
                        "JOIN  PLANES ON FLIGHTS.planeId = PLANES.id " +
                        "WHERE TICKETS.isDeleted=false and TICKETS.userId=%d", currentUserId);
        try (Connection conn = dataSource.getConnection();
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery(sql)) {
            while (rs.next()) {
                ticket = extractTicketFromRS(rs);
                allTickets.add(ticket);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return allTickets;
    }

    @Override
    public List<Ticket> getAllTickets() {
       List<Ticket> allTickets = new ArrayList<>();
       Ticket ticket;
        String sql = "SELECT * FROM TICKETS " +
                "JOIN  USERS ON TICKETS.userId=USERS.id " +
                "JOIN  FLIGHTS ON TICKETS.flightId=FLIGHTS.id " +
                "JOIN  PLANES ON FLIGHTS.planeId = PLANES.id " +
                "WHERE TICKETS.isDeleted=false";
        try (Connection conn = dataSource.getConnection();
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery(sql)) {
            while (rs.next()) {
                ticket = extractTicketFromRS(rs);
                allTickets.add(ticket);
            }
        }

        catch (SQLException e) {
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
        String sql = String.format("SELECT * FROM TICKETS " +
                "JOIN  USERS ON TICKETS.userId=USERS.id " +
                "JOIN  FLIGHTS ON TICKETS.flightId=FLIGHTS.id " +
                "JOIN  PLANES ON FLIGHTS.planeId = PLANES.id " +
                "WHERE TICKETS.isDeleted=false and TICKETS.id=%d", currentId);
        try (Connection conn = dataSource.getConnection();
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery(sql)) {
            while (rs.next())
                ticket = extractTicketFromRS(rs);
        }

        catch (SQLException e) {
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

    private Ticket extractTicketFromRS (ResultSet rs) throws SQLException {
        return Ticket.builder()
                .id(rs.getLong("id"))
                .flight(FlightRowMapper.getInstance().mapRow(rs,1))
                .user(UserRowMapper.getInstance().mapRow(rs,1))
                .isBusiness(rs.getBoolean("isBusiness"))
                .hasLuggage(rs.getBoolean("hasLuggage"))
                .placePriority(rs.getBoolean("placePriority"))
                .price(rs.getLong("price"))
                .isDeleted(false).build();

    }
}
