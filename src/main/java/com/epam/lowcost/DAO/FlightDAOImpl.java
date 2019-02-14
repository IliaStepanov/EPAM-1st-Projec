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

    public FlightDAOImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    @Override
    public List<Flight> getAllFlights() {
        List<Flight> flights = new ArrayList<>();
        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM FLIGHTS JOIN  PLANES " +
                     "ON FLIGHTS.plane_id = PLANES.id WHERE  FLIGHTS.ISDELETED = false ")) {
            while (rs.next()) {
                flights.add(extractFlightFromRS(rs));
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
                " ON FLIGHTS.plane_id = PLANES.id  WHERE FLIGHTS.id = '%d' AND FLIGHTS.isDeleted=FALSE", id);
        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                return extractFlightFromRS(rs);

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
        String sql = String.format("INSERT INTO Flights (initialPrice, plane_id, departureDate,arrivalDate,isDeleted)" +
                        " VALUES (%d,%d,'%s','%s','%s')", price,
                plane_id,
                DateFormatter.format(depatureDate),
                DateFormatter.format(arrivalDate),
                "FALSE");
        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement()) {
            int lines = stmt.executeUpdate(sql, 1);
            System.out.println(lines);
            if (lines != 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        rs.last();
                        flight.setId(rs.getLong("id"));
                    }
                } catch (SQLException e) {
                    System.out.println("there");
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

    private Flight extractFlightFromRS(ResultSet rs) throws SQLException {
        return Flight.builder()
                .id(rs.getLong("id"))
                .initialPrice(rs.getLong("initialPrice"))
                .plane(extractPlaneFromRS(rs))
                .departureDate(rs.getTimestamp("departureDate").toLocalDateTime())
                .arrivalDate(rs.getTimestamp("arrivalDate").toLocalDateTime())
                .isDeleted(rs.getBoolean("isDeleted"))
                .build();
    }

    private Plane extractPlaneFromRS(ResultSet rs) throws SQLException {
        return Plane.builder().id(rs.getLong("plane_id"))
                .businessPlacesNumber(rs.getInt("businessPlacesNumber"))
                .economPlacesNumber(rs.getInt("economPlacesNumber"))
                .model(rs.getString("model"))
                .build();
    }


}

