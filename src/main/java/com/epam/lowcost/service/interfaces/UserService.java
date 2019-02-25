package com.epam.lowcost.service.interfaces;

import com.epam.lowcost.model.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    List<User> getAllUsers();

    User getById(long userId);

    User addUser(User user);

    User updateUser(User user);

    String deleteUser(long userId);

    User verifyUser(String log, String pass);

    Map<String, Object> getUsersByPage(int pageId, int numberOfUsersOnPage);

    int countUsers();
}
