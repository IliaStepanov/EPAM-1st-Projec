package com.epam.lowcost.DAO;

import com.epam.lowcost.model.Flight;
import com.epam.lowcost.model.Plane;
import com.epam.lowcost.model.Ticket;
import com.epam.lowcost.model.User;

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
                        "JOIN  PLANES ON FLIGHTS.plane_id = PLANES.id " +
                        "WHERE TICKETS.isDeleted=false and TICKETS.userId=%d", currentUserId);
        try (Connection conn = dataSource.getConnection();
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery(sql)) {
            while (rs.next()) {
                long id = rs.getLong("id");
                Flight flight = extractFlightFromRS(rs);
                User user = extractUserFromRS(rs);
                boolean isBusiness = rs.getBoolean("isBusiness");
                boolean hasLuggage = rs.getBoolean("hasLuggage");
                boolean placePriority = rs.getBoolean("placePriority");
                long price = rs.getLong("price");
                ticket = new Ticket(id, user, flight, isBusiness, hasLuggage, placePriority, price, false);
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
                "JOIN  PLANES ON FLIGHTS.plane_id = PLANES.id " +
                "WHERE TICKETS.isDeleted=false";
        try (Connection conn = dataSource.getConnection();
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery(sql)) {
            while (rs.next()) {
                long id = rs.getLong("id");
                User user = extractUserFromRS(rs);
                Flight flight = extractFlightFromRS(rs);
                boolean isBusiness = rs.getBoolean("isBusiness");
                boolean hasLuggage = rs.getBoolean("hasLuggage");
                boolean placePriority = rs.getBoolean("placePriority");
                long price = rs.getLong("price");
                ticket = new Ticket(id, user, flight, isBusiness, hasLuggage, placePriority, price, false);
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
                "JOIN  PLANES ON FLIGHTS.plane_id = PLANES.id " +
                "WHERE TICKETS.isDeleted=false and TICKETS.id=%d", currentId);
        try (Connection conn = dataSource.getConnection();
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery(sql)) {
            while (rs.next()) {
                long id = rs.getLong("id");
                Flight flight = extractFlightFromRS(rs);
                User user = extractUserFromRS(rs);
                boolean isBusiness = rs.getBoolean("isBusiness");
                boolean hasLuggage = rs.getBoolean("hasLuggage");
                boolean placePriority = rs.getBoolean("placePriority");
                long price = rs.getLong("price");
                ticket = new Ticket(id, user, flight, isBusiness, hasLuggage, placePriority, price, false);
            }
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
            if (lines == 1) {
                return ticket;
            }
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


    private User extractUserFromRS(ResultSet rs) throws SQLException {
        return User.builder()
                .id(rs.getLong("USERS.id"))
                .email(rs.getString("email"))
                .password(rs.getString("password"))
                .isAdmin(rs.getBoolean("isAdmin"))
                .firstName(rs.getString("firstName"))
                .lastName(rs.getString("lastName"))
                .documentInfo(rs.getString("documentInfo"))
                .birthday(rs.getTimestamp("birthday").toLocalDateTime())
                .isDeleted(rs.getBoolean("isDeleted"))
                .build();
    }

    private Flight extractFlightFromRS(ResultSet rs) throws SQLException {
        return Flight.builder()
                .id(rs.getLong("FLIGHTS.id"))
                .initialPrice(rs.getLong("initialPrice"))
                .plane(new Plane())
                .plane(Plane.builder()
                        .id(rs.getLong("plane_id"))
                        .businessPlacesNumber(rs.getInt("businessPlacesNumber"))
                        .economPlacesNumber(rs.getInt("economPlacesNumber"))
                        .model(rs.getString("model"))
                        .build())
                .departureDate(rs.getTimestamp("departureDate").toLocalDateTime())
                .arrivalDate(rs.getTimestamp("arrivalDate").toLocalDateTime())
                .isDeleted(rs.getBoolean("isDeleted"))
                .build();
    }
}
