package com.epam.lowcost.util;

import com.epam.lowcost.model.Airport;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AirportRowMapper implements RowMapper<Airport> {

    @Override
    public Airport mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Airport.builder()
                .code(rs.getString("iataCode"))
                .cityRus(rs.getString("cityRus"))
                .cityEng(rs.getString("cityEng"))
                .countryRus(rs.getString("countryRus"))
                .countryEng(rs.getString("countryEng"))
                .nameEng(rs.getString("nameEng"))
                .nameRus(rs.getString("nameRus"))
                .build();
    }
}
