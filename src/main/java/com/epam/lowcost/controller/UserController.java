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
import java.util.Map;

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
    @PostMapping (value = "/users/addUser")
    public String addUser(@RequestParam Map<String,String> params, Model model){
        model.addAttribute("user", userService.addUser(new User(1,
                params.get("email"),
                params.get("password"),
                Boolean.valueOf(params.get("isAdmin")),
                params.get("firstName"),
                params.get("lastName"),
                params.get("documentInfo"),
                LocalDateTime.parse(params.get("birthday")))));
        model.addAttribute("message","User successfully added");
                return "users";
    }
    @PostMapping (value = "/users/updateUser")
    public String updateUser(@RequestParam Map<String,String> params, Model model){
       model.addAttribute("user",userService.updateUser(new User(Long.valueOf(params.get("id")),
               params.get("email"),
               params.get("password"),
               Boolean.valueOf(params.get("isAdmin")),
               params.get("firstName"),
               params.get("lastName"),
               params.get("documentInfo"),
               LocalDateTime.parse(params.get("birthday")))));
       model.addAttribute("message","User successfully updated");
       return "users";
    }
}
