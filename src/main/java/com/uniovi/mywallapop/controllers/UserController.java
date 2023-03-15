package com.uniovi.mywallapop.controllers;

import com.uniovi.mywallapop.entities.User;
import com.uniovi.mywallapop.services.RolesService;
import com.uniovi.mywallapop.services.SecurityService;
import com.uniovi.mywallapop.services.UsersService;
import com.uniovi.mywallapop.validators.SignUpFormValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
@Controller
public class UserController {
    @Autowired
    private UsersService usersService;
    @Autowired
    private SecurityService securityService;
    @Autowired
    private RolesService rolesService;
    @Autowired
    private SignUpFormValidator signUpFormValidator;

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String signup(@Validated User user, BindingResult result) {
        signUpFormValidator.validate(user,result);
        if(result.hasErrors()){
            return "signup";
        }
        user.setRole(rolesService.getRoles()[0]);
        usersService.addUser(user);
        securityService.autoLogin(user.getDni(), user.getPasswordConfirm());
        return "redirect:home";
    }
    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String signup(Model model) {
        model.addAttribute("user", new User());
        return "signup";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }
}
