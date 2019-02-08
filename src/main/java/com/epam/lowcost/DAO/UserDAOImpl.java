package com.epam.lowcost.DAO;

import com.epam.lowcost.model.User;
import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {

    private DataSource dataSource;

    public UserDAOImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> allUsers = new ArrayList<>();
        User user;
        try (Connection conn = dataSource.getConnection();
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery("SELECT * FROM USERS")) {
            while (rs.next()) {

                long id = rs.getLong("id");
                String email = rs.getString("email");
                String password = rs.getString("password");
                boolean isAdmin = rs.getBoolean("isAdmin");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                String documentInfo = rs.getString("documentInfo");
                LocalDateTime birthday = rs.getTimestamp("birthday").toLocalDateTime();

                user = new User(id, email, password, isAdmin, firstName, lastName, documentInfo, birthday);

                allUsers.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allUsers;
    }
}
