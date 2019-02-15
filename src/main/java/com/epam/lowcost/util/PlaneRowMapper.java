package com.epam.lowcost.util;

import com.epam.lowcost.model.Plane;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public final class PlaneRowMapper implements RowMapper<Plane> {
    private static final RowMapper<Plane> planeRowMapperInstance = new PlaneRowMapper();

    private PlaneRowMapper() {
    }

    public static RowMapper<Plane> getInstance() {
        return planeRowMapperInstance;
    }

    @Override
    public final Plane mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Plane.builder()
                .id(rs.getLong("planeId"))
                .model(rs.getString("model"))
                .businessPlacesNumber(rs.getInt("businessPlacesNumber"))
                .economPlacesNumber(rs.getInt("economPlacesNumber"))
                .isDeleted(rs.getBoolean("isDeleted"))
                .build();
    }
}