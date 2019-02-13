package com.epam.lowcost.DAO;

import com.epam.lowcost.model.Flight;
import com.epam.lowcost.model.Plane;
import com.epam.lowcost.util.DateFormatter;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class FlightDAOImpl implements FlightDAO {
    private DataSource dataSource;
    private PlaneDAO planeDAO;

    public FlightDAOImpl(DataSource dataSource, PlaneDAO planeDAO) {
        this.dataSource = dataSource;
        this.planeDAO = planeDAO;
    }


    @Override
    public List<Flight> getAllFlights() {
        List<Flight> flights = new ArrayList<>();
        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM FLIGHTS  ")) {
            while (rs.next()) {
                Long id = rs.getLong("id");
                Long price = rs.getLong("initialPrice");
                LocalDateTime departureDate = rs.getTimestamp("departureDate").toLocalDateTime();
                LocalDateTime arrivalDate = rs.getTimestamp("arrivalDate").toLocalDateTime();
                Long plane_id = rs.getLong("plane_id");
                Plane plane = planeDAO.getById(plane_id);
                Boolean isDeleted = rs.getBoolean("isDeleted");
                if (!isDeleted)
                    flights.add(Flight.builder().id(id).initialPrice(price).plane(plane).
                            departureDate(departureDate).arrivalDate(arrivalDate).build());

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flights;
    }

    @Override
    public Flight getFlightById(Long id) {
        Flight flight = new Flight();
        String sql = String.format("SELECT * FROM FLIGHTS WHERE id = '%d' AND isDeleted=FALSE", id);
        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                Long price = rs.getLong("initialPrice");
                LocalDateTime departureDate = rs.getTimestamp("departureDate").toLocalDateTime();
                LocalDateTime arrivalDate = rs.getTimestamp("arrivalDate").toLocalDateTime();
                Long plane_id = rs.getLong("plane_id");
                Plane plane = planeDAO.getById(plane_id);
                return Flight.builder().id(id).plane(plane).initialPrice(price).
                        departureDate(departureDate).arrivalDate(arrivalDate).build();

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flight;
    }

    @Override
    public Flight addNewFlight(Flight flight) {
        Long price = flight.getInitialPrice();
        LocalDateTime depatureDate = flight.getDepartureDate();
        LocalDateTime arrivalDate = flight.getArrivalDate();
        Long plane_id = flight.getPlane().getId();

        String sql = String.format("INSERT INTO Flight (initialPrice, plane_id, departureDate,arrivalDate,isDeleted)" +
                " VALUES (%d,%d,'%s','%s','%s')", price, plane_id, DateFormatter.format(depatureDate), DateFormatter.format(arrivalDate), "FALSE");
        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement()) {
            int lines = stmt.executeUpdate(sql);
            if (lines == 1) {
                ResultSet rs = stmt.executeQuery("SELECT * FROM FLIGHTS");
                rs.last();
                long newId = rs.getLong("id");
                flight.setId(newId);
                return flight;
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        flight = null;
        return flight;
    }

    @Override
    public Flight deleteFlight(Long id) {
        Flight flight = getFlightById(id);
        flight.setDeleted(true);
        String sql = String.format("UPDATE Flight SET isDeleted = TRUE WHERE id = '%d'", id);
        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement()) {
            int lines = stmt.executeUpdate(sql);
            if (lines == 1) {
                return flight;
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        flight = null;
        return flight;

    }

    @Override
    public Flight updateFlight(Flight flight) {
        if (getFlightById(flight.getId())==null)
            return null;
        String sql = String.format("UPDATE Flight SET initialPrice='%d',departureDate='%s'," +
                        "arrivalDate='%s', plane_id='%d' WHERE id = %d",
                flight.getInitialPrice(),
                DateFormatter.format(flight.getDepartureDate()),
                DateFormatter.format(flight.getArrivalDate()),
                flight.getPlane().getId(),
                flight.getId());
        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement()) {
            int lines = stmt.executeUpdate(sql);
            if (lines == 1) {
                return flight;
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        flight = null;
        return flight;
    }


}

