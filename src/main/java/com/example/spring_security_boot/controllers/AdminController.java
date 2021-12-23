package com.example.spring_security_boot.controllers;

import com.example.spring_security_boot.model.User;
import com.example.spring_security_boot.service.RoleService;
import com.example.spring_security_boot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping
    public String printUsers(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("user", user);
        model.addAttribute("newUser", new User());
        model.addAttribute("users", userService.getAllUsers());
        return "/admin";
    }

    @PostMapping("/new")
    public String addNewUser(@ModelAttribute("newUser") User user, @RequestParam("roles") ArrayList<Long> roles) {
        user.setRoles(roles.stream().map(roleService::getRole).collect(Collectors.toSet()));
        userService.addUser(user);
        return "redirect:/admin";
    }

    @PostMapping("/update/{id}")
    public String updateUser(@ModelAttribute("user") User user, @RequestParam("roles") List<Long> roles) {
        user.setRoles(roles.stream().map(roleService::getRole).collect(Collectors.toSet()));
        userService.updateUser(user);

        return "redirect:/admin";
    }

    @PostMapping(value = "/delete/{id}")
    public String delete(@PathVariable("id") int id) {
        userService.deleteUser(id);

        return "redirect:/admin";
    }
}
