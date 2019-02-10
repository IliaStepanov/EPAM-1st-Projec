package com.epam.lowcost.sevice;

import com.epam.lowcost.DAO.UserDAO;
import com.epam.lowcost.model.User;

import java.util.List;

public class UserServiceImpl implements UserService {

    private UserDAO userDAO;

    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    @Override
    public User getById(long userId) {
        return userDAO.getById(userId);
    }

    @Override
    public User addUser(User user) {
        return userDAO.addUser(user);
    }

    @Override
    public User updateUser(User user) {
        return userDAO.updateUser(user);
    }
}
