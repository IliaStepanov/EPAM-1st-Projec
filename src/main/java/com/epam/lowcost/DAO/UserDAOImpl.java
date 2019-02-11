package com.epam.lowcost.DAO;

import com.epam.lowcost.model.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

                allUsers.add(User.builder()
                        .id(id).email(email).password(password)
                        .isAdmin(isAdmin).firstName(firstName)
                        .lastName(lastName).documentInfo(documentInfo)
                        .birthday(birthday).build());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allUsers;
    }

    @Override
    public User getById(long userId) {
        User user = null;
        String sql = String.format("SELECT * FROM USERS WHERE id='%d'", userId);
        try (Connection connection = dataSource.getConnection();
             Statement stm = connection.createStatement();
             ResultSet rs = stm.executeQuery(sql)) {
            if (rs.next()) {
                long id = rs.getLong("id");
                String email = rs.getString("email");
                String password = rs.getString("password");
                boolean isAdmin = rs.getBoolean("isAdmin");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                String documentInfo = rs.getString("documentInfo");
                LocalDateTime birthday = rs.getTimestamp("birthday").toLocalDateTime();

                return User.builder().id(id).email(email)
                        .password(password).isAdmin(isAdmin)
                        .firstName(firstName).lastName(lastName)
                        .documentInfo(documentInfo).birthday(birthday).build();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public User addUser(User user) {
        DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
        String sql = String.format(
                "INSERT INTO USERS (email, password, isAdmin, " +
                        "firstName, lastName, documentInfo, birthday) " +
                        "VALUES ('%s', '%s',%b,'%s','%s','%s','%s')",

                user.getEmail(), user.getPassword(), user.isAdmin(),
                user.getFirstName(), user.getLastName(), user.getDocumentInfo(), f.format(user.getBirthday()));

        try (Connection connection = dataSource.getConnection();
             Statement stm = connection.createStatement()
        ) {
            int insert = stm.executeUpdate(sql);
            if (insert == 1) {
                ResultSet rs = stm.executeQuery("SELECT * FROM USERS");
                rs.last();
                long newId = rs.getLong("id");
                user.setId(newId);
                return user;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        user = null;
        return user;
    }

    @Override
    public User updateUser(User user) {
        DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
        String sql = String.format(
                "UPDATE USERS SET email='%s',password='%s',isAdmin='%b',firstName='%s'," +
                        "lastName='%s',documentInfo='%s',birthday='%s' WHERE id=%d",
                user.getEmail(), user.getPassword(), user.isAdmin(), user.getFirstName(),
                user.getLastName(), user.getDocumentInfo(), f.format(user.getBirthday()), user.getId());
        try (Connection conn = dataSource.getConnection();
             Statement stm = conn.createStatement()) {
            int update = stm.executeUpdate(sql);
            if (update == 1) {
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        user = null;
        return user;
    }
}
