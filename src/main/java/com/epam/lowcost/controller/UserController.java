package com.epam.lowcost.controller;

import com.epam.lowcost.model.User;
import com.epam.lowcost.service.interfaces.UserService;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import static com.epam.lowcost.util.Constants.DEFAULT_NUMBER_OF_USERS_ON_PAGE;
import static com.epam.lowcost.util.EndPoints.*;

@Controller
@RequestMapping(value = USER)
@SessionAttributes({"sessionUser", "number"})
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping(value = ALL + "/{pageId}")
    public String getAllUsers(@PathVariable int pageId, @ModelAttribute(value = "sessionUser") User sessionUser, ModelMap model) {
        if (!sessionUser.isAdmin()) {
            return "redirect:" + TICKETS + SELF;
        }

        int numberOfUsersOnPage = (int) model.getOrDefault("number", DEFAULT_NUMBER_OF_USERS_ON_PAGE);

        Map<String, Object> pageRepresentation = userService.getUsersByPage(pageId, numberOfUsersOnPage);

        model.addAttribute("pagesNum", pageRepresentation.get("pagesNum"));
        model.addAttribute("users", pageRepresentation.get("users"));
        model.addAttribute("pageId", pageRepresentation.get("pageId"));
        return USERSPAGE;
    }

    @GetMapping(value = PAGE)
    public String setUsersByPage(@RequestParam String number, @RequestParam String fromPage, Model model) {
        model.addAttribute("number", Integer.parseInt(number));
        return "redirect:" + fromPage + FIRST_PAGE;
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
        return USERSPAGE;
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
        return USERSPAGE;
    }

    @PostMapping(value = UPDATE)
    public String updateUser(@RequestParam Map<String, String> params, Model model) {

        User userToUpdate = userService.verifyUser(params.get("email"), params.get("password"));
        //s=Сделай верифай бай айди
        if (userToUpdate == null) {
            model.addAttribute("message", "No such user or it has been deleted!");
        } else {
            userToUpdate.setEmail(params.get("email"));
            userToUpdate.setFirstName(params.get("firstName"));
            userToUpdate.setLastName(params.get("lastName"));
            userToUpdate.setDocumentInfo(params.get("documentInfo"));
            userToUpdate.setBirthday(LocalDate.parse(params.get("birthday")).atStartOfDay());
            userToUpdate.setPassword(params.get("password"));
            System.out.println(userToUpdate);
            userService.updateUser(userToUpdate);
            model.addAttribute("user", userToUpdate);
            model.addAttribute("message", "User successfully updated");
        }
        return USERSPAGE;
    }

    @PostMapping(value = CHANGE_PASSWORD)
    public String changePassword(@ModelAttribute("sessionUser") User sessionUser, @RequestParam Map<String, String> params, Model model) {
        User user = userService.verifyUser(sessionUser.getEmail(), params.get("oldPassword"));
        if (user == null) {
            model.addAttribute("message", "Wrong password");
            return SETTINGSPAGE;
        }
        if (!params.get("newPassword").equals(params.get("newPassword2"))) {
            model.addAttribute("message", "Passwords did not match!");
            return SETTINGSPAGE;
        }
        sessionUser.setPassword(params.get("newPassword"));
        userService.updateUser(sessionUser);
        model.addAttribute("message", "Passwords changed successfully!");
        return SETTINGSPAGE;
    }

    @PostMapping(value = ENROLL)
    public String createUser(@RequestParam Map<String, String> params, Model model) {
        ResourceBundle bundle = ResourceBundle.getBundle("lang");
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
        model.addAttribute("message", bundle.getString("lang.successfullRegistration"));
        return LOGIN;

    }

    @GetMapping(value = SETTINGS)
    public String settings(ModelMap modelMap) {
        User sessionUser = (User) modelMap.get("sessionUser");
        modelMap.addAttribute("sessionUser", sessionUser);
        return SETTINGSPAGE;
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
