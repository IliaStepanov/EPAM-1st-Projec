package com.epam.lowcost.util;

import com.epam.lowcost.model.Flight;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public final class FlightRowMapper implements RowMapper<Flight> {
    private static final RowMapper<Flight> planeRowMapperInstance = new FlightRowMapper();

    private FlightRowMapper() {
    }

    public static RowMapper<Flight> getInstance() {
        return planeRowMapperInstance;
    }

    @Override
    public final Flight mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Flight.builder()
                .id(rs.getLong("FLIGHTS.id"))
                .initialPrice(rs.getLong("initialPrice"))
                .plane(PlaneRowMapper.getInstance().mapRow(rs,1))
                .departureDate(rs.getTimestamp("departureDate").toLocalDateTime())
                .arrivalDate(rs.getTimestamp("arrivalDate").toLocalDateTime())
                .isDeleted(rs.getBoolean("isDeleted"))
                .build();
    }
}