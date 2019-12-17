package com.javacode.controller;

import com.javacode.model.Role;
import com.javacode.model.User;
import com.javacode.service.RoleService;
import com.javacode.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Controller
public class UsersController {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Authentication authentication) {
        if(authentication != null) {
            return "redirect:/profile";
        }
        return "login";
    }

    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public String getError() {
        return "403";
    }

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String getProfile() {
        return "profile";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String getAdmin(Model model) {
        model.addAttribute("usersList", userService.allUsersList());
        return "admin";
    }

                                        //Registration
    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String register(Authentication authentication) {
        if(authentication != null) {
            return "redirect:/profile";
        }
        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@RequestParam(value = "username") String username, @RequestParam(value = "userpassword") String userpassword) {
        Set<Role> roles = new HashSet<>();
        roles.add(roleService.getRoleById(1L));
        String codepass = passwordEncoder.encode(userpassword);
        User user = new User(username, codepass);
        user.setRoles(roles);
        userService.addUser(user);
        return "redirect:/login";
    }

                                //UpdateDelete


    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String updateIndex(@RequestParam(value = "id") int id, Model model) {
        User user = userService.getUserById((long) id);
        model.addAttribute("user", user);
        return "update";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String commitUpdate(@RequestParam(value = "username") String username,
                               @RequestParam(value = "id") int id) {
        User user = userService.getUserById((long) id);
        user.setUsername(username);
        userService.updateUser(user);
        return "redirect:/admin";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String delete(@RequestParam(value = "id") int id) {
        userService.removeUserById((long) id);
        return "redirect:/admin";
    }
}
