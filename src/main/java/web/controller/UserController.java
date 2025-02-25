package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

import java.util.List;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public String getUsers(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "users";
    }

    @PostMapping("/addUser")
    public String addUser(@RequestParam String name) {
        User user = new User();
        user.setName(name);
        userService.addUser(user);
        return "redirect:/users";
    }

    @PostMapping("/deleteUser")
    public String deleteUser(@RequestParam Long id) {
        userService.deleteUser(id);
        return "redirect:/users";
    }

    @PostMapping("/updateUser")
    public String updateUser(@RequestParam Long id, @RequestParam String name) {
        User user = new User();
        user.setId(id);
        user.setName(name);
        userService.updateUser(user);
        return "redirect:/users";
    }
}
