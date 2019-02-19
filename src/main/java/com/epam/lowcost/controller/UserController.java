package com.epam.lowcost.controller;

import com.epam.lowcost.model.User;
import com.epam.lowcost.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;

@Controller
@RequestMapping(value = "/user")
@SessionAttributes(value = "sessionUser")
public class UserController {

    @Autowired
    UserService userService;


    @GetMapping(value = "/all")
    public String getAllUsers(@ModelAttribute(value = "sessionUser") User sessionUser, Model model) {
        if (!sessionUser.isAdmin()) {
            return "redirect:/tickets/self";
        }
        model.addAttribute("users", userService.getAllUsers());
        return "users";
    }

    @GetMapping
    public String getById(@ModelAttribute(value = "sessionUser") User sessionUser,@RequestParam long id, Model model) {
        if (!sessionUser.isAdmin()) {
            return "redirect:/tickets/self";
        }
        model.addAttribute("user", userService.getById(id));
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
                        .birthday(LocalDateTime.parse(params.get("birthday")))
                        .isDeleted(false)
                        .build());
        model.addAttribute("user", user );
        model.addAttribute("message", "User successfully added");
        return "users";
    }

    @PostMapping(value = "/update")
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
                        .birthday(LocalDateTime.parse(params.get("birthday")))
                        .build());
        if (user == null) {
            model.addAttribute("message", "No such user or it has been deleted!");
        }
        if(params.get("userUpdate").equals("fromUser")){
            model.addAttribute("sessionUser", user);
            return "redirect:/tickets/self";
        }
        else {
            model.addAttribute("user", user);
            model.addAttribute("message", "User successfully updated");
        }
        return "users";
    }

    @PostMapping(value = "registration")
    public String registration(@RequestParam Map<String, String> params,Model model){
        userService.addUser(
                User.builder()
                        .email(params.get("email"))
                        .password(params.get("password"))
                        .isAdmin(Boolean.valueOf(params.get("isAdmin")))
                        .firstName(params.get("firstName"))
                        .lastName(params.get("lastName"))
                        .documentInfo(params.get("documentInfo"))
                        .birthday(LocalDateTime.parse(params.get("birthday")))
                        .isDeleted(false)
                        .build());
        model.addAttribute("message", "Successfully registered. Please Log in. ");
        return "login";

    }

    @GetMapping(value = "settings")
    public String settings(@ModelAttribute("sessionUser") User sessionUser){
            return "userSettings";
    }
    @PostMapping(value = "/change-password")
    public String changePassword(@ModelAttribute("sessionUser") User sessionUser, @RequestParam Map<String, String> params, Model model){
        if(userService.verifyUser(sessionUser.getEmail() ,params.get("oldPassword"))!=null){
                if(params.get("newPassword").equals(params.get("newPassword2"))){
                    sessionUser.setPassword(params.get("newPassword"));
                    userService.updateUser(sessionUser);
                }
        }
        return "redirect:/tickets/self";
    }

    @PostMapping(value = "/delete")
    public String deleteUser(@ModelAttribute(value = "sessionUser") User sessionUser,@RequestParam long id, Model model) {
        if (!sessionUser.isAdmin()) {
            return "redirect:/tickets/self";
        }
        model.addAttribute("message", userService.deleteUser(id));
        return "users";
    }

    @GetMapping(value = "/navpage")
    public String nav(){return "navigationPanel";}

}
