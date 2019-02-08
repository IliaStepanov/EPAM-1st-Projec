package com.epam.lowcost.controller;

import com.epam.lowcost.model.User;
import com.epam.lowcost.sevice.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserServiceImpl userService;

    @GetMapping(value = "/list")
    public List<User> greeting(){
        System.out.println("USER CONTROLER");
        return userService.getAllUsers();
    }
}
