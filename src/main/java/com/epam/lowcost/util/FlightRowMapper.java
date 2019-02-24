package com.epam.lowcost.util;

import com.epam.lowcost.model.Airport;
import com.epam.lowcost.model.Flight;
import com.epam.lowcost.model.Plane;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public final class FlightRowMapper implements RowMapper<Flight> {
    private RowMapper<Plane> planeRowMapper;
    private RowMapper<Airport> airportRowMapper;

    public FlightRowMapper(RowMapper<Plane> planeRowMapper, RowMapper<Airport> airportRowMapper) {
        this.planeRowMapper = planeRowMapper;
        this.airportRowMapper = airportRowMapper;
    }

    @Override
    public final Flight mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Flight.builder()
                .id(rs.getLong("FLIGHTS.id"))
                .initialPrice(rs.getLong("initialPrice"))
                .plane(planeRowMapper.mapRow(rs, rowNum))
                .departureAirport(airportRowMapper.mapRow(rs,1))
                .arrivalAirport(airportRowMapper.mapRow(rs,0))
                .departureDate(rs.getTimestamp("departureDate").toLocalDateTime())
                .arrivalDate(rs.getTimestamp("arrivalDate").toLocalDateTime())
                .businessPrice(rs.getLong("businessPrice"))
                .luggagePrice(rs.getLong("luggagePrice"))
                .placePriorityPrice(rs.getLong(("placePriorityPrice")))
                .isDeleted(rs.getBoolean("isDeleted"))
                .build();
    }
}

