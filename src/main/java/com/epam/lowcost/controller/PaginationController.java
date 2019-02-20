package com.epam.lowcost.controller;
import com.epam.lowcost.DAO.interfaces.UserDAO;
import com.epam.lowcost.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class PaginationController {
    @Autowired
    UserDAO userDAO;

    @RequestMapping(value="/viewUser/{pageId}")
    public String edit(@PathVariable int pageId, Model model){
        int usersByPage = 1;
        if(pageId <= 0){
            pageId = 1;
        }
        int pagesNum = (int)Math.ceil(userDAO.getAllUsers().size()/ usersByPage);
        if(pageId >= pagesNum){
            pageId = pagesNum;
        }

        List<User> list = userDAO.getUsersByPage(pageId, usersByPage);

        model.addAttribute("pagesNum", pagesNum);
        model.addAttribute("users", list);
        model.addAttribute("pageId",pageId);
        return "viewUser";
    }
}
