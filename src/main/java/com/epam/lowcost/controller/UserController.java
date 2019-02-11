package com.epam.lowcost.controller;

import com.epam.lowcost.model.User;
import com.epam.lowcost.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.Map;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping(value = "/all")
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "users";
    }

    @GetMapping(value = "/id")
    public String getById(@RequestParam long id, Model model) {
        model.addAttribute("user", userService.getById(id));
        return "users";
    }

    @PostMapping(value = "/add")
    public String addUser(@RequestParam Map<String, String> params, Model model) {
        model.addAttribute("user", userService.addUser(
                User.builder()
                        .email(params.get("email"))
                        .password(params.get("password"))
                        .isAdmin(Boolean.valueOf(params.get("isAdmin")))
                        .firstName(params.get("firstName"))
                        .lastName(params.get("lastName"))
                        .documentInfo(params.get("documentInfo"))
                        .birthday(LocalDateTime.parse(params.get("birthday")))
                        .build()));

        model.addAttribute("message", "User successfully added");
        return "users";
    }

    @PostMapping(value = "/update")
    public String updateUser(@RequestParam Map<String, String> params, Model model) {
        model.addAttribute("user", userService.updateUser(
                User.builder()
                        .id(Long.valueOf(params.get("id")))
                        .email(params.get("email"))
                        .password(params.get("password"))
                        .isAdmin(Boolean.valueOf(params.get("isAdmin")))
                        .firstName(params.get("firstName"))
                        .lastName(params.get("lastName"))
                        .documentInfo(params.get("documentInfo"))
                        .birthday(LocalDateTime.parse(params.get("birthday")))
                        .build()));
        model.addAttribute("message", "User successfully updated");
        return "users";
    }


}
