package com.uniovi.mywallapop.controllers;

import com.uniovi.mywallapop.services.RolesService;
import com.uniovi.mywallapop.services.SecurityService;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.Banner;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.uniovi.mywallapop.entities.*;
import com.uniovi.mywallapop.services.UsersService;
@Controller
public class UsersController {

    @Autowired
    private UsersService usersService;
    @Autowired
    private SecurityService securityService;

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String signup(@ModelAttribute("user") User user, Model model) {
        usersService.addUser(user);
        securityService.autoLogin(user.getDni(), user.getPasswordConfirm());
        return "redirect:home";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }


    @RequestMapping(value = { "/home" }, method = RequestMethod.GET)
    public String home() {
        return "home";
    }

}
