package com.epam.lowcost.util;

import com.epam.lowcost.model.User;
import lombok.Data;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public  final class UserRowMapper implements RowMapper<User> {
    private static final RowMapper<User> userRowMapperInstance = new UserRowMapper();

    private UserRowMapper() {
    }

    public static RowMapper<User> getInstance(){
        return userRowMapperInstance;
    }

    @Override
    public final User mapRow(ResultSet rs, int rowNum) throws SQLException {

        return User.builder()
                .id(rs.getLong("id"))
                .email(rs.getString("email"))
                .password(rs.getString("password"))
                .isAdmin(rs.getBoolean("isAdmin"))
                .firstName(rs.getString("firstName"))
                .lastName(rs.getString("lastName"))
                .documentInfo(rs.getString("documentInfo"))
                .birthday(rs.getTimestamp("birthday").toLocalDateTime())
                .isDeleted(rs.getBoolean("isDeleted"))
                .build();
    }
}
