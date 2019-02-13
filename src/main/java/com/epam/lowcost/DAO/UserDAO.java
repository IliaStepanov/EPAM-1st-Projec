package com.epam.lowcost.DAO;

import com.epam.lowcost.model.User;

import java.util.List;

public interface UserDAO {
    List<User> getAllUsers();

    User getById(long userId);

    User findByEmail(String log, String pass);

    User addUser(User user);

    User updateUser(User user);

    String deleteUser(long userId);
}
