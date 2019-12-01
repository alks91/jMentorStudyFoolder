package com.javacode.controller;

import com.javacode.model.User;
import com.javacode.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class UsersController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getIndex(Model model) {
        model.addAttribute("usersList", userService.allUsersList());
        return "index";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registration(@RequestParam(value = "email") String email, @RequestParam(value = "name") String name) {
        userService.addUser(new User(email, name));
        return "redirect:/";
    }

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String updateIndex(@RequestParam(value = "id") int id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "update";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String commitUpdate(@RequestParam(value = "email") String email,
                               @RequestParam(value = "name") String name, @RequestParam(value = "id") int id) {
        User user = userService.getUserById(id);
        user.setEmail(email);
        user.setName(name);
        userService.updateUser(user);
        return "redirect:/";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String delete(@RequestParam(value = "id") int id) {
        userService.removeUserById(id);
        return "redirect:/";
    }
}
