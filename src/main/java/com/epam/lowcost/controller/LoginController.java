package com.epam.lowcost.controller;

import com.epam.lowcost.model.User;
import com.epam.lowcost.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import java.util.Map;


@Controller
@RequestMapping(value = "/login")
@SessionAttributes(value = "sessionUser")
public class LoginController {
    @Autowired
    UserService userService;

    @GetMapping
    public String auth(@ModelAttribute("sessionUser") User user) {
        if (user.isAdmin()) {
            return "admin";
        } else {
            return "redirect:/tickets/myTickets";
        }
    }
    @GetMapping(value = "/registration")
    public String registration (){
        return "registration";
    }

    @PostMapping
    public String login(@RequestParam Map<String, String> logPass, Model model) {

        User user = userService.verifyUser(logPass.get("email"), logPass.get("password"));

        if (user == null) {
            model.addAttribute("message", "No such User found, or password is wrong. Maybe you want to: ");
        } else if (user.isAdmin()) {
            model.addAttribute("sessionUser", user);
            return "admin";
        } else if (!user.isAdmin()) {
            model.addAttribute("sessionUser", user);
            model.addAttribute("id", user.getId());
            return "redirect:/tickets/myTickets";
        }
        return "login";
    }

    @GetMapping(value = "/log-out")
    public String logOut(SessionStatus sessionStatus) {
        sessionStatus.setComplete();
        return "login";
    }


}
