package com.epam.lowcost.DAO.implementations;

import com.epam.lowcost.DAO.interfaces.UserDAO;
import com.epam.lowcost.exceptions.DatabaseErrorException;
import com.epam.lowcost.model.User;
import com.epam.lowcost.util.DateFormatter;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.util.List;

public class UserDAOImpl extends AbstractDAOImpl<User> implements UserDAO {

    public UserDAOImpl(DataSource dataSource, RowMapper<User> rowMapper) {
        super(dataSource, rowMapper);
    }

    @Override
    public List<User> getAllUsers() throws DatabaseErrorException {
        String sql = "SELECT * FROM USERS WHERE ISDELETED=FALSE";
        return executeSqlSelect(sql);
    }

    @Override
    public User getById(long userId) throws DatabaseErrorException {
        String sql = String.format("SELECT * FROM USERS WHERE id='%d' and isDeleted=false", userId);
        return executeSqlSelect(sql).get(0);
    }


    @Override
    public User findByEmail(String email) {
        try {
            JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
            return jdbcTemplate.queryForObject("SELECT * FROM USERS WHERE email=?", rowMapper, email);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public User addUser(User user) throws DatabaseErrorException {
        String sql = String.format(
                "INSERT INTO USERS (email, password, isAdmin, " +
                        "firstName, lastName, documentInfo, birthday, isDeleted) " +
                        "VALUES ('%s', '%s',%b,'%s','%s','%s','%s',false)",

                user.getEmail(), user.getPassword(), user.isAdmin(),
                user.getFirstName(), user.getLastName(), user.getDocumentInfo(), DateFormatter.format(user.getBirthday()));
        return executeSqlInsert(user, sql);
    }

    @Override
    public User updateUser(User user) throws DatabaseErrorException {
        if (getById(user.getId()) == null) {
            return null;
        }
        String updateSql = String.format(
                "UPDATE USERS SET email='%s',password='%s',isAdmin='%b',firstName='%s'," +
                        "lastName='%s',documentInfo='%s',birthday='%s' WHERE id=%d",
                user.getEmail(), user.getPassword(), user.isAdmin(), user.getFirstName(),
                user.getLastName(), user.getDocumentInfo(), DateFormatter.format(user.getBirthday()), user.getId());
        return executeSqlUpdate(updateSql) == 1 ? user : null;
    }

    @Override
    public String deleteUser(long userId) throws DatabaseErrorException {
        String sql = String.format("UPDATE USERS SET isDeleted=true WHERE id=%d", userId);
        return executeSqlUpdate(sql) == 1 ? String.format("User %d successfully deleted", userId)
                : "User was not deleted";
    }


    @Override
    public List<User> getUsersByPage(int pageId, int usersByPage) {
        pageId = pageId - 1;
        if (pageId > 0) {
            pageId = pageId * usersByPage;
        }
        return executeSqlSelect("SELECT * FROM USERS WHERE isDeleted=false LIMIT " + (pageId) + "," + usersByPage);
    }

    @Override
    public int countUsers() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        return jdbcTemplate.queryForObject("SELECT COUNT(id) FROM USERS WHERE isDeleted=false", Integer.class);
    }
}
