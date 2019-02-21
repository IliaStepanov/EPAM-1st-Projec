package com.epam.lowcost.service.implementations;

import com.epam.lowcost.DAO.interfaces.UserDAO;
import com.epam.lowcost.model.User;
import com.epam.lowcost.service.interfaces.UserService;
import org.mindrot.jbcrypt.BCrypt;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
        return userDAO.addUser(user);
    }

    @Override
    public User updateUser(User user) {
        user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
        return userDAO.updateUser(user);
    }

    @Override
    public String deleteUser(long userId) {
        return userDAO.deleteUser(userId);
    }

    @Override
    public User verifyUser(String email, String password) {
        User user = userDAO.findByEmail(email);
        if (user != null && BCrypt.checkpw(password, user.getPassword())) return user;
        return null;
    }

    @Override
    public Map<String, Object> getUsersByPage(int pageId, int total) {
        if (pageId <= 0) {
            pageId = 1;
        }
        int users = userDAO.countUsers();
        int pagesNum;
        if (users % total != 0) {
            pagesNum = (users / total + 1);
        } else {
            pagesNum = (users / total);
        }
        if (pageId >= pagesNum) {
            pageId = pagesNum;
        }

        Map<String, Object> pageInfo = new HashMap<>();
        pageInfo.put("users", userDAO.getUsersByPage(pageId, total));
        pageInfo.put("pagesNum", pagesNum);


        return pageInfo;
    }

    @Override
    public int countUsers() {
        return userDAO.countUsers();
    }
}
