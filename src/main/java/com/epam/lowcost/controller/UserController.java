package com.epam.lowcost.controller;

import com.epam.lowcost.model.User;
import com.epam.lowcost.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.epam.lowcost.util.EndPoints.*;

@Controller
@RequestMapping(value = USER)
@SessionAttributes({"sessionUser", "number"})
public class UserController {

    @Autowired
    UserService userService;


    @GetMapping(value = ALL + "/{pageId}")
    public String getAllUsers(@PathVariable int pageId, @ModelAttribute(value = "sessionUser") User sessionUser, ModelMap model) {
        if (!sessionUser.isAdmin()) {
            return "redirect:" + TICKETS + SELF;
        }

        int usersByPage = (int) model.getOrDefault("number", 5);

        Map<String, Object> pageRepresentation = userService.getUsersByPage(pageId, usersByPage);

        model.addAttribute("pagesNum", pageRepresentation.get("pagesNum"));
        model.addAttribute("users", pageRepresentation.get("users"));
        model.addAttribute("pageId", pageRepresentation.get("pageId"));
        return "users";
    }

    @GetMapping(value = SET_USERS_BY_PAGE)
    public String setUsersByPage(@RequestParam String number, Model model) {
        model.addAttribute("number", Integer.parseInt(number));
        return "redirect:" + USER + ALL + FIRST_PAGE;
    }


    @GetMapping
    public String getById(@ModelAttribute(value = "sessionUser") User sessionUser, @RequestParam long id, Model model) {
        if (!sessionUser.isAdmin()) {
            return "redirect:" + TICKETS + SELF;
        }
        List<User> user = new ArrayList<>();
        user.add(userService.getById(id));
        model.addAttribute("users", user);
        model.addAttribute("message", "Here is your User!");
        return "users";
    }

    @PostMapping
    public String addUser(@RequestParam Map<String, String> params, Model model) {
        User user = userService.addUser(
                User.builder()
                        .email(params.get("email"))
                        .password(params.get("password"))
                        .isAdmin(Boolean.valueOf(params.get("isAdmin")))
                        .firstName(params.get("firstName"))
                        .lastName(params.get("lastName"))
                        .documentInfo(params.get("documentInfo"))
                        .birthday(LocalDate.parse(params.get("birthday")).atStartOfDay())
                        .isDeleted(false)
                        .build());
        model.addAttribute("user", user);
        model.addAttribute("message", "User successfully added");
        return "users";
    }

    @PostMapping(value = UPDATE)
    public String updateUser(@RequestParam Map<String, String> params, Model model) {
        User user = userService.updateUser(
                User.builder()
                        .id(Long.valueOf(params.get("id")))
                        .email(params.get("email"))
                        .password(params.get("password"))
                        .isAdmin(Boolean.valueOf(params.get("isAdmin")))
                        .firstName(params.get("firstName"))
                        .lastName(params.get("lastName"))
                        .documentInfo(params.get("documentInfo"))
                        .birthday(LocalDate.parse(params.get("birthday")).atStartOfDay())
                        .build());
        if (user == null) {
            model.addAttribute("message", "No such user or it has been deleted!");
        }
        if (params.get("userUpdate").equals("fromUser")) {
            model.addAttribute("sessionUser", user);
            return "redirect:" + TICKETS + SELF;
        } else {
            model.addAttribute("user", user);
            model.addAttribute("message", "User successfully updated");
        }
        return "users";
    }

    @PostMapping(value = ENROLL)
    public String createUser(@RequestParam Map<String, String> params, Model model) {
        userService.addUser(
                User.builder()
                        .email(params.get("email"))
                        .password(params.get("password"))
                        .isAdmin(Boolean.valueOf(params.get("isAdmin")))
                        .firstName(params.get("firstName"))
                        .lastName(params.get("lastName"))
                        .documentInfo(params.get("documentInfo"))
                        .birthday(LocalDate.parse(params.get("birthday")).atStartOfDay())
                        .isDeleted(false)
                        .build());
        model.addAttribute("message", "Successfully registered. Please Log in. ");
        return "login";

    }

    @GetMapping(value = SETTINGS)
    public String settings(@ModelAttribute("sessionUser") User sessionUser) {
        return "settings";
    }

    @PostMapping(value = CHANGE_PASSWORD)
    public String changePassword(@ModelAttribute("sessionUser") User sessionUser, @RequestParam Map<String, String> params, Model model) {
        User user = userService.verifyUser(sessionUser.getEmail(), params.get("oldPassword"));
        if (user == null) {
            model.addAttribute("message", "Wrong password");
            return "settings";
        }
        if (!params.get("newPassword").equals(params.get("newPassword2"))) {
            model.addAttribute("message", "Passwords did not match!");
            return "settings";
        }
        sessionUser.setPassword(params.get("newPassword"));
        userService.updateUser(sessionUser);
        model.addAttribute("message", "Passwords changed successfully!");
        return "settings";
    }

    @PostMapping(value = DELETE)
    public String deleteUser(@RequestParam long id, ModelMap model) {
        User sessionUser = (User) model.get("sessionUser");
        if (!sessionUser.isAdmin()) {
            return "redirect:" + TICKETS + SELF;
        }
        if (sessionUser.getId() == id) {
            model.addAttribute("message", "You cant delete yourself!");
            return "redirect:" + USER + ALL + FIRST_PAGE;
        }
        model.addAttribute("message", userService.deleteUser(id));
        return "redirect:" + USER + ALL + FIRST_PAGE;
    }

}
