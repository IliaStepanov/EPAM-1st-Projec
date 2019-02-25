package com.epam.lowcost.controller;

import com.epam.lowcost.model.Plane;
import com.epam.lowcost.model.User;
import com.epam.lowcost.service.interfaces.PlaneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static com.epam.lowcost.util.EndPoints.*;

@Controller
@RequestMapping(value = PLANE)
@SessionAttributes({"sessionUser", "number"})
public class PlaneController {

    @Autowired
    PlaneService planeService;

    @GetMapping(value = ALL + "/{pageId}")
    public String getAllPlanes(@PathVariable int pageId, ModelMap model ) {
        if (!((User) model.get("sessionUser")).isAdmin()) {
            return "redirect:" + TICKETS + SELF;
        }
        int planesByPage = (int) model.getOrDefault("number", 1);
        Map<String, Object> pageRepresentation = planeService.getPlanesByPage(pageId, planesByPage);

        model.addAttribute("pagesNum", pageRepresentation.get("pagesNum"));
        model.addAttribute("planes", pageRepresentation.get("planes"));
        model.addAttribute("pageId", pageRepresentation.get("pageId"));

        return PLANESPAGE;
    }
    @GetMapping(value = SET_PLANES_BY_PAGE)
    public String setUsersByPage(@RequestParam String number, @RequestParam String fromPage, Model model) {
        model.addAttribute("number", Integer.parseInt(number));
        return "redirect:" + fromPage + FIRST_PAGE;
    }

    @GetMapping
    public String getById(@RequestParam long id, Model model) {
        model.addAttribute("plane", planeService.getById(id));
        model.addAttribute("message", "Here is your Plane!");
        return PLANESPAGE;
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
        return PLANESPAGE;
    }

    @PostMapping(value = UPDATE)
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
        return PLANESPAGE;
    }

    @PostMapping(value = DELETE)
    public String deletePlane(@RequestParam long id, Model model) {
        model.addAttribute("message", planeService.deletePlane(id));
        return PLANESPAGE;
    }
}
