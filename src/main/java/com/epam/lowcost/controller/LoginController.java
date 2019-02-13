package com.epam.lowcost.controller;

import com.epam.lowcost.model.User;
import com.epam.lowcost.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;


@Controller
@RequestMapping(value = "/login")
public class LoginController {
    @Autowired
    UserService userService;

    @PostMapping
    public String login(@RequestParam Map<String, String> logPass, Model model) {

        User user = userService.verifyUser(logPass.get("login"), logPass.get("password"));

        if (user == null) {
            model.addAttribute("message", "No such User found. Maybe you want to: ");
        } else if (user.isAdmin() || !user.isAdmin()) {

            return "admin";
        }
        return "login";
    }


}
