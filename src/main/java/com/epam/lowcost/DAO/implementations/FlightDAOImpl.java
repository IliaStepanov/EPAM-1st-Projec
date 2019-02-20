package com.epam.lowcost.DAO.implementations;

import com.epam.lowcost.DAO.interfaces.FlightDAO;
import com.epam.lowcost.model.Flight;
import com.epam.lowcost.util.DateFormatter;
import org.springframework.jdbc.core.RowMapper;

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
    private RowMapper<Flight> flightRowMapper;

    public FlightDAOImpl(DataSource dataSource, RowMapper<Flight> flightRowMapper) {
        this.dataSource = dataSource;
        this.flightRowMapper = flightRowMapper;
    }


    @Override
    public List<Flight> getAllFlights() {
        List<Flight> flights = new ArrayList<>();
        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM FLIGHTS JOIN  PLANES " +
                     "ON FLIGHTS.planeId = PLANES.id WHERE  FLIGHTS.ISDELETED = false ORDER BY FLIGHTS.id DESC ")) {
            while (rs.next()) {
                flights.add(flightRowMapper.mapRow(rs, 1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flights;
    }

    @Override
    public Flight getById(Long id) {
        Flight flight = null;
        String sql = String.format("SELECT * FROM FLIGHTS JOIN  PLANES" +
                " ON FLIGHTS.planeId = PLANES.id  WHERE FLIGHTS.id = '%d' AND FLIGHTS.isDeleted=FALSE", id);
        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                return flightRowMapper.mapRow(rs, 1);

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
        Long planeId = flight.getPlane().getId();
        String departureAirport = flight.getDepartureAirport().toUpperCase();
        String arrivalAirport = flight.getArrivalAirport().toUpperCase();
        String sql = String.format("INSERT INTO Flights (initialPrice, planeId, departureDate," +
                        "arrivalDate,isDeleted, departureAirport,arrivalAirport)" +
                        " VALUES (%d,%d,'%s','%s','%s', '%s','%s')", price,
                planeId,
                DateFormatter.format(depatureDate),
                DateFormatter.format(arrivalDate),
                "FALSE", departureAirport, arrivalAirport);
        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement()) {
            int lines = stmt.executeUpdate(sql, 1);
            if (lines != 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        rs.last();
                        flight.setId(rs.getLong("id"));
                    }
                } catch (SQLException e) {
                    e.printStackTrace();

                }
                return flight;
            }
        } catch (SQLException k) {
            k.printStackTrace();

        }
        flight = null;
        return flight;
    }

    @Override
    public Flight deleteFlight(Long id) {
        Flight flight = getById(id);
        flight.setDeleted(true);
        String sql = String.format("UPDATE Flights SET isDeleted = TRUE WHERE id = '%d'", id);
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
        if (getById(flight.getId()) == null)
            return null;
        String sql = String.format("UPDATE Flights SET initialPrice='%d',departureDate='%s'," +
                        "arrivalDate='%s', planeId='%d',departureAirport = '%s', arrivalAirport = '%s' WHERE id = %d",
                flight.getInitialPrice(),
                DateFormatter.format(flight.getDepartureDate()),
                DateFormatter.format(flight.getArrivalDate()),
                flight.getPlane().getId(),
                flight.getDepartureAirport().toUpperCase(),
                flight.getArrivalAirport().toUpperCase(),
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

    @Override
    public List<Flight> getByFromToDate(String departureAirport, String arrivalAirport,
                                        LocalDateTime departureDateFrom, LocalDateTime departureDateTo) {
        List<Flight> flights = new ArrayList<>();
        String sql = String.format("SELECT * FROM FLIGHTS JOIN  PLANES" +
                        " ON FLIGHTS.planeId = PLANES.id  WHERE FLIGHTS.departureAirport = '%s' " +
                        "AND FLIGHTS.arrivalAirport = '%s' AND" +
                        " FLIGHTS.departureDate BETWEEN '%s' AND '%s' " +
                        "AND FLIGHTS.isDeleted=FALSE",
                departureAirport.toUpperCase(), arrivalAirport.toUpperCase(),
                DateFormatter.format(departureDateFrom), DateFormatter.format(departureDateTo.plusDays(1)));
        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                flights.add(flightRowMapper.mapRow(rs, 1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flights;
    }


}

