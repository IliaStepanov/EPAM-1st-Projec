package com.epam.lowcost.controller;

import com.epam.lowcost.model.User;
import com.epam.lowcost.service.UserService;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import java.util.Map;


@Controller
@RequestMapping(value = "/entry")
@SessionAttributes(value = "sessionUser")
public class LoginController {
    @Autowired
    UserService userService;

    @GetMapping
    public String auth(@ModelAttribute("sessionUser") User sessionUser) {
        return "userPage";
    }

    @GetMapping(value = "/admin-panel")
    public String toAdminPanel(@ModelAttribute("sessionUser") User sessionUser) {
        return "admin";
    }


    @GetMapping(value = "/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping
    public String login(@RequestParam Map<String, String> logPass, Model model) {

        User user = userService.verifyUser(logPass.get("email"), logPass.get("password"));

        if (user == null) {
            model.addAttribute("message", "No such User found, or password is wrong. Maybe you want to: ");
        } else {
            model.addAttribute("sessionUser", user);
            return "redirect:/tickets/self";
        }
        return "login";
    }

    @GetMapping(value = "/log-out")
    public String logOut(SessionStatus sessionStatus) {
        sessionStatus.setComplete();
        return "redirect:/entry";
    }

}
