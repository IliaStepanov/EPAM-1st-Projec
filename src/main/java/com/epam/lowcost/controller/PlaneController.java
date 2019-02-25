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

import static com.epam.lowcost.util.Constants.DEFAULT_NUMBER_OF_PLANES_ON_PAGE;
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
        int numberOFPlanesOnPage = (int) model.getOrDefault("number", DEFAULT_NUMBER_OF_PLANES_ON_PAGE);
        Map<String, Object> pageRepresentation = planeService.getPlanesByPage(pageId, numberOFPlanesOnPage);

        model.addAttribute("pagesNum", pageRepresentation.get("pagesNum"));
        model.addAttribute("planes", pageRepresentation.get("planes"));
        model.addAttribute("pageId", pageRepresentation.get("pageId"));

        return PLANESPAGE;
    }
    @GetMapping(value = PAGE)
    public String setUsersByPage(@RequestParam String number, @RequestParam String fromPage, Model model) {
        model.addAttribute("number", Integer.parseInt(number));
        return "redirect:" + fromPage + FIRST_PAGE;
    }

    @GetMapping (value = ADD)
    public String addNewPlane() {
        return ADDPLANE;
    }

    @GetMapping
    public String getById(@RequestParam long id, Model model) {
        model.addAttribute("plane", planeService.getById(id));
//        model.addAttribute("message", "Here is your Plane!");
        return PLANESSETTINGS;
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
        return "redirect:" + PLANE+ ALL+ FIRST_PAGE;
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
        return "redirect:" + PLANE + ALL + FIRST_PAGE;

    }

    @PostMapping(value = DELETE)
    public String deletePlane(@RequestParam long id, Model model) {
        model.addAttribute("message", planeService.deletePlane(id));
        return "redirect:" + PLANE + ALL + FIRST_PAGE;
    }
}
