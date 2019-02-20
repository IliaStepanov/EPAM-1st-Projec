package com.epam.lowcost.service.interfaces;

import com.epam.lowcost.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    User getById(long userId);

    User addUser(User user);

    User updateUser(User user);

    String deleteUser(long userId);

    User verifyUser(String log, String pass);

    List<User> getUsersByPage(int pageId, int total);
}
