package com.epam.lowcost.controller;

import com.epam.lowcost.model.User;
import com.epam.lowcost.sevice.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller

public class UserController {

    @Autowired
    UserServiceImpl userService;

    @GetMapping(value = "/users/listAllUsers")
    public String listAllUsers(Model model)
    {
        model.addAttribute("users",userService.getAllUsers());
        return "users";
    }
    @PostMapping (value = "/users/getById")
    public String getById(@RequestParam long id, Model model)
    {   long userId = Long.valueOf(id);
        model.addAttribute("user",userService.getById(id));
        return "users";
    }
    @GetMapping (value = "/users/addUser")
    public String addUser(Model model){
        model.addAttribute("user", userService.addUser(new User(1,"ExampleEmail1@google.com", "examplePassword1",false,"AddedUserNAME",
                "AddedUserLASTNAME","№1234 bestpassport ever",LocalDateTime.of(1990,02,02,00,00))));
        return "users";
    }
    @GetMapping (value = "/users/updateUser")
    public String updateUser(Model model){
       model.addAttribute("user",userService.updateUser(new User(5,"UpdatedEmail@google.com", "examplePassword1",false,"UpdatedName",
                "UpdatedLastname","№1234 bestpassport ever",LocalDateTime.of(2050,02,02,00,00))));
       model.addAttribute("message","User successfully updated.");
       return "users";
    }
}
