package com.epam.lowcost.DAO;

import com.epam.lowcost.model.Flight;
import com.epam.lowcost.model.Plane;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class FlightDAOImpl implements FlightDAO {
    private DataSource dataSource;

    public FlightDAOImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Flight> getAllFlights() {
        List<Flight> flights = new ArrayList<>();
        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM FLIGHT  ")) {
            while (rs.next()) {
                Long id = rs.getLong("id");
                Long price = rs.getLong("initialPrice");
                LocalDateTime departureDate = rs.getTimestamp("departureDate").toLocalDateTime();
                LocalDateTime arrivalDate = rs.getTimestamp("arrivalDate").toLocalDateTime();
                Long plane_id = rs.getLong("plane_id");
                Plane plane = getPlaneById(plane_id);
                flights.add(Flight.builder().id(id).initialPrice(price).plane(plane).
                        departureDate(departureDate).arrivalDate(arrivalDate).build());

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flights;
    }

    @Override
    public Flight getFlightById(long id) {
        Flight flight = new Flight();
        String sql = String.format("SELECT * FROM FLIGHT WHERE id = '%d'", id);
        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                Long price = rs.getLong("initialPrice");
                LocalDateTime departureDate = rs.getTimestamp("departureDate").toLocalDateTime();
                LocalDateTime arrivalDate = rs.getTimestamp("arrivalDate").toLocalDateTime();
                Long plane_id = rs.getLong("plane_id");
                Plane plane = getPlaneById(plane_id);
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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
        String sql = String.format("INSERT INTO Flight (initialPrice, plane_id, departureDate,arrivalDate)" +
                " VALUES ('%d','%d','%s','%s')", price, plane_id, formatter.format(depatureDate), formatter.format(arrivalDate));
        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement()) {
            int lines = stmt.executeUpdate(sql);
            if (lines == 1) {
                ResultSet rs = stmt.executeQuery("SELECT * FROM FLIGHT JOIN PLANES ON plane_id=id");
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

//    @Override
//    public void deleteFlight(int id) {
//        try (Connection conn = dataSource.getConnection();
//             Statement stmt = conn.createStatement();
//             ResultSet rs = stmt.executeQuery("DELETE * FROM FLIGHT WHERE id = id")) {
//        } catch (SQLException e) {
//            e.printStackTrace();
//
//        }
//    }

    @Override
    public Flight updateFlight(Flight flight) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
        String sql = String.format("UPDATE Flight SET (initialPrice,departureDate,arrivalDate)" +
                        " VALUES ('%d','%s','%s) WHERE id = '%d'", flight.getInitialPrice(),
                formatter.format(flight.getDepartureDate()), formatter.format(flight.getArrivalDate()), flight.getId());
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

    public Plane getPlaneById(Long id) {
        Plane plane = new Plane();
        String sql = String.format("SELECT * FROM PLANES WHERE id = '%d'", id);
        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                String model = rs.getString("model");
                Integer businessPlacesNumber = rs.getInt("businessPlacesNumber");
                Integer economPlacesNumber = rs.getInt("economPlacesNumber");
                plane = Plane.builder().model(model).businessPlacesNumber(businessPlacesNumber).
                        economPlacesNumber(economPlacesNumber).build();

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return plane;
    }
}

