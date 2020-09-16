package jm.security.example.controller;

import jm.security.example.model.User;
import jm.security.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/")
    public String getHomePage() {
        return "index";
    }

    @GetMapping(value = "/login")
    public String getLoginPage() {
        return "login";
    }

    @GetMapping(value = "/all")
    public String getAllUsers(Model model) {
        String title = "Все пользователи";
        List<User> users = userService.allUsers();
        for (User u : users) {
            System.out.println(u.toString());
        }
        model.addAttribute("title", title);
        model.addAttribute("listUsers", users);
        return "users";
    }
    @PostMapping(value = "/adduser", produces = "text/html; charset=utf-8")
    public String addUser(User user) {
        System.out.println("Добавить" + user.toString());
            userService.add(user);
        return "redirect:/users";
    }

    @GetMapping(value = "edit/{id}")
    public String editUser(@PathVariable long id, Model model) {
        System.out.println("Редактировать ");
        User us = userService.getById(id);
        String title = "Привет";
        model.addAttribute("title", title);
        model.addAttribute("user", us);
        System.out.println("Нашли - " + us.toString());
        userService.edit(us);
        return "edituser";
    }
    @PostMapping(value = "/edituser")
    public String updateUser(@ModelAttribute User user) {
        System.out.println("Пришли на форму редактирования");
        System.out.println("новый юзер - " + user.toString());
        userService.edit(user);
        return "redirect:/users";
    }


    @GetMapping(value = "/user")
    public String getUser(Model model, Principal principal) {
        System.out.println("Получаем юзера");
        User us = userService.getUserByName(principal.getName());
        System.out.println(us.toString());
        String title = "Добрый день";
        model.addAttribute("user", us);

        return "user";
    }

    @GetMapping("/remove/{id}")
    public String removeUser(@PathVariable long id) {
        userService.delete(id);
        return "redirect:/users";
    }

}
