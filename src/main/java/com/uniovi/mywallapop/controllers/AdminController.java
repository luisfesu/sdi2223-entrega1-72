package com.uniovi.mywallapop.controllers;

import com.uniovi.mywallapop.entities.User;
import com.uniovi.mywallapop.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class AdminController {
    @Autowired
    private UsersService usersService;

    @RequestMapping(value = "/admin/user/list")
    public String getList(Model model) {
        model.addAttribute("usersList", usersService.getUsers());
        return "user/list";
    }
    @RequestMapping("/admin/user/list/update")
    public String updateList(Model model){
        model.addAttribute("usersList", usersService.getUsers() );
        return "user/list :: tableUsers";
    }
    @RequestMapping("/admin/user/delete/{id}")
    public String delete(@PathVariable Long id) {
        usersService.deleteUser(id);
        return "redirect:/admin/user/list";
    }
    @RequestMapping(value = "/admin/user/deleteMultiple", method = RequestMethod.POST)
    public String deleteMultiple(@RequestParam("usersToDelete") List<Long> userIds) {
        for (Long userId : userIds) {
            usersService.deleteUser(userId);
        }
        return "redirect:/admin/user/list";
    }
}
