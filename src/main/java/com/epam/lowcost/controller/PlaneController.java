package com.epam.lowcost.controller;

import com.epam.lowcost.service.PlaneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/plane")
public class PlaneController {

    @Autowired
    PlaneService planeService;

    @GetMapping(value = "/all")
    public String listAllUsers(Model model)
    {
        model.addAttribute("planes",planeService.getAllPlanes());
        return "planes";
    }

    @PostMapping(value = "/id")
    public String getById(@RequestParam long id, Model model)
    {   model.addAttribute("plane",planeService.getById(id));
        return "planes";
    }

    @PostMapping (value = "/add")
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
}
