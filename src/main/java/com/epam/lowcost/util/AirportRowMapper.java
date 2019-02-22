package com.epam.lowcost.util;

import com.epam.lowcost.model.Airport;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AirportRowMapper implements RowMapper<Airport> {

    @Override
    public Airport mapRow(ResultSet rs, int rowNum) throws SQLException {
        int table=0;
        if (rowNum ==0) table=7;
        int column = rs.findColumn("AIRPORTS.code");
        if (column!=1) column+=table;
        return Airport.builder()
                .code(rs.getString(column))
                .cityRus(rs.getString(column+1))
                .cityEng(rs.getString(column+2))
                .countryRus(rs.getString(column+3))
                .countryEng(rs.getString(column+4))
                .nameEng(rs.getString(column+5))
                .nameRus(rs.getString(column+6))
                .build();
    }
}
