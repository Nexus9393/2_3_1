package com.example.controller;

import com.example.model.User;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String listUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "users";
    }

    @PostMapping("/save")
    public String saveUser(@RequestParam("name") String name,
                           @RequestParam("email") String email,
                           @RequestParam(value = "id", required = false) Long id) {

        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setId(id);

        if (id == null || id == 0) {
            userService.addUser(user);
        } else {
            userService.updateUser(user);
        }

        return "redirect:/";
    }

    @PostMapping("/delete")
    public String deleteUser(@RequestParam("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/";
    }
}