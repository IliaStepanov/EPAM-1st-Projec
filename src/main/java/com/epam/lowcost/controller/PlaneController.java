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
}
