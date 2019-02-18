package com.epam.lowcost.controller;

import com.epam.lowcost.model.Plane;
import com.epam.lowcost.service.PlaneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
@RequestMapping(value = "/plane")
public class PlaneController {

    @Autowired
    PlaneService planeService;

    @GetMapping(value = "/all")
    public String getAllPlanes(Model model) {
        model.addAttribute("planes", planeService.getAllPlanes());
        return "planes";
    }

    @GetMapping
    public String getById(@RequestParam long id, Model model) {
        model.addAttribute("plane", planeService.getById(id));
        model.addAttribute("message", "Here is your Plane!");
        return "planes";
    }

    @PostMapping
    public String addPlane(@RequestParam Map<String, String> params, Model model) {
        model.addAttribute("plane", planeService.addPlane(
                Plane.builder()
                        .model(params.get("model"))
                        .businessPlacesNumber(Integer.valueOf(params.get("businessPlacesNumber")))
                        .economPlacesNumber(Integer.valueOf(params.get("economPlacesNumber")))
                        .isDeleted(false)
                        .build()));

        model.addAttribute("message", "Plane successfully added");
        return "planes";
    }

    @PostMapping(value = "/update")
    public String updatePlane(@RequestParam Map<String, String> params, Model model) {
        Plane plane = planeService.updatePlane(
                Plane.builder()
                        .id(Long.valueOf(params.get("id")))
                        .model(params.get("model"))
                        .businessPlacesNumber(Integer.valueOf(params.get("businessPlacesNumber")))
                        .economPlacesNumber(Integer.valueOf(params.get("economPlacesNumber")))
                        .build());
        if (plane == null) {
            model.addAttribute("message", "No such plane or it has been deleted!");
        } else {
            model.addAttribute("plane", plane);
            model.addAttribute("message", "Plane seccessfully updated");
        }
        return "planes";
    }

    @PostMapping(value = "/delete")
    public String deletePlane(@RequestParam long id, Model model) {
        model.addAttribute("message", planeService.deletePlane(id));
        return "planes";
    }
}
