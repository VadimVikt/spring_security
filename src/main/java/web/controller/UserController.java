package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class UserController {
	@Autowired
	UserService userService;

	@RequestMapping(value = "hello", method = RequestMethod.GET)
	public String printWelcome(ModelMap model) {
		List<String> messages = new ArrayList<>();
		messages.add("Hello!");
		messages.add("I'm Spring MVC-SECURITY application");
		messages.add("5.2.0 version by sep'19 ");
		model.addAttribute("messages", messages);
		return "hello";
	}

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String loginPage() {
        return "login";
    }

    @GetMapping(value = "/all")
	public String listUsers(Model model) {
		String title = "Пользователи";
		model.addAttribute("title", title);
		model.addAttribute("listUsers", userService.listUsers());
		List<User> users = userService.listUsers();
		for (User a : users) {
			System.out.println(a.getUsername());
		}
		return "users";
	}
	@PostMapping(value = "/adduser", produces = "text/html; charset=utf-8")
	public String addUser(Model model, User user) {
		if (user.getId() == 0) {
			userService.addUser(user);
		} else {
			userService.updateUser(user);
		}
		return "redirect:/users";
	}
	@GetMapping("/remove/{id}")
	public String removeUser(@PathVariable long id) {
		userService.removeUserById(id);
		return "redirect:/users";
	}
}