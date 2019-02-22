package com.epam.lowcost.DAO.implementations;

import com.epam.lowcost.DAO.interfaces.FlightDAO;
import com.epam.lowcost.exceptions.DatabaseErrorException;
import com.epam.lowcost.model.Flight;
import com.epam.lowcost.util.DateFormatter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.util.List;

public class FlightDAOImpl extends AbstractDAOImpl<Flight> implements FlightDAO {

    public FlightDAOImpl(DataSource dataSource, RowMapper<Flight> rowMapper) {
        super(dataSource, rowMapper);
    }

    @Override
    public List<Flight> getAllFlights() throws DatabaseErrorException {
        String sql = "SELECT * FROM FLIGHTS JOIN PLANES " +
                "ON FLIGHTS.PLANEID = PLANES.ID WHERE  FLIGHTS.ISDELETED = false";
        return executeSqlSelect(sql);
    }

    @Override
    public Flight getById(long id) throws DatabaseErrorException {
        String sql = String.format("SELECT * FROM FLIGHTS JOIN  PLANES" +
                " ON FLIGHTS.planeId = PLANES.id  WHERE FLIGHTS.id = '%d' AND FLIGHTS.isDeleted=FALSE", id);
        return executeSqlSelect(sql).get(0);
    }

    @Override
    public Flight addNewFlight(Flight flight) throws DatabaseErrorException {
        Long price = flight.getInitialPrice();
        LocalDateTime depatureDate = flight.getDepartureDate();
        LocalDateTime arrivalDate = flight.getArrivalDate();
        Long planeId = flight.getPlane().getId();
        String departureAirport = flight.getDepartureAirport().toUpperCase();
        String arrivalAirport = flight.getArrivalAirport().toUpperCase();
        Long businessPrice = flight.getBusinessPrice();
        Long placePriorityPrice = flight.getPlacePriorityPrice();
        Long luggagePrice = flight.getLuggagePrice();
        String sql = String.format("INSERT INTO Flights (initialPrice, planeId, departureDate," +
                        "arrivalDate,isDeleted, departureAirport,arrivalAirport,businessPrice,placePriorityPrice,luggagePrice)" +
                        " VALUES (%d,%d,'%s','%s','%s', '%s','%s',%d,%d,%d5)", price,
                planeId,
                DateFormatter.format(depatureDate),
                DateFormatter.format(arrivalDate),
                "FALSE", departureAirport, arrivalAirport, businessPrice, placePriorityPrice, luggagePrice);
        return executeSqlInsert(flight, sql);
    }

    @Override
    public String deleteFlight(long id) throws DatabaseErrorException {
        String sql = String.format("UPDATE Flights SET isDeleted = TRUE WHERE id = '%d'", id);
        return executeSqlUpdate(sql) == 1 ? String.format("User %d successfully deleted", id)
                : "User was not deleted";
    }

    @Override
    public Flight updateFlight(Flight flight) throws DatabaseErrorException {
        if (getById(flight.getId()) == null)
            return null;
        String sql = String.format("UPDATE Flights SET initialPrice='%d',departureDate='%s'," +
                        "arrivalDate='%s', planeId='%d',departureAirport = '%s', arrivalAirport = '%s'," +
                        "luggagePrice=%d, placePriorityPrice=%d, businessPrice=%d WHERE id = %d",
                flight.getInitialPrice(),
                DateFormatter.format(flight.getDepartureDate()),
                DateFormatter.format(flight.getArrivalDate()),
                flight.getPlane().getId(),
                flight.getDepartureAirport().toUpperCase(),
                flight.getArrivalAirport().toUpperCase(),
                flight.getLuggagePrice(),
                flight.getPlacePriorityPrice(),
                flight.getBusinessPrice(),
                flight.getId());
        return executeSqlUpdate(sql) == 1 ? flight : null;
    }

    @Override
    public List<Flight> getByFromToDate(String departureAirport, String arrivalAirport,
                                        LocalDateTime departureDateFrom, LocalDateTime departureDateTo)
            throws DatabaseErrorException {
        String sql = String.format("SELECT * FROM FLIGHTS JOIN  PLANES" +
                        " ON FLIGHTS.planeId = PLANES.id  WHERE FLIGHTS.departureAirport = '%s' " +
                        "AND FLIGHTS.arrivalAirport = '%s' AND" +
                        " FLIGHTS.departureDate BETWEEN '%s' AND '%s' " +
                        "AND FLIGHTS.isDeleted=FALSE",
                departureAirport.toUpperCase(), arrivalAirport.toUpperCase(),
                DateFormatter.format(departureDateFrom), DateFormatter.format(departureDateTo.plusDays(1)));
        return executeSqlSelect(sql);
    }

    @Override
    public List<Flight> getFlightsByPage(int pageId, int flightsByPage) {
        pageId = pageId - 1;
        if (pageId > 0) {
            pageId = pageId * flightsByPage;
        }
        String sql = "SELECT * FROM FLIGHTS JOIN PLANES ON FLIGHTS.PLANEID = PLANES.ID WHERE  FLIGHTS.ISDELETED = false LIMIT " + (pageId) + "," + flightsByPage;
            return executeSqlSelect(sql);
    }

    @Override
    public int countFlights() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        return jdbcTemplate.queryForObject("SELECT COUNT(id) FROM FLIGHTS WHERE isDeleted=false", Integer.class);
    }
}

