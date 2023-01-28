package com.example.sec.controller;

import com.example.sec.model.User;
import com.example.sec.repository.UserRepository;
import com.example.sec.service.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Controller
@RequestMapping("/admin")
public class AdminController {

    private final AppService appService;
    private final UserRepository userRepository;

    @Autowired
    public AdminController(AppService appService, UserRepository userRepository) {
        this.appService = appService;
        this.userRepository = userRepository;
    }

    @GetMapping("/")
    public String loginPage() {
        return "login";
    }


    @GetMapping()
    public String adminPage() {
        return "admin";
    }

    @GetMapping("/new")
    public String addUser(User user) {
        return "create";
    }

    @PostMapping("/new")
    public String newUser(@ModelAttribute("user") User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "create";
        } else {
            appService.addUser(user);
            return "redirect:/users";
        }
    }

    @GetMapping("/delete/{id}")
    public String delete(@ModelAttribute("user") User user, @PathVariable("id") long id) {
        appService.deleteUser(user.getId());
        return "redirect:/";
    }

    @GetMapping("edit/{id}")
    public String updateUser(@PathVariable("id") long id, Model model) {
        model.addAttribute(appService.findUser(id));
        return "edit";
    }

    @PatchMapping("/edit")
    public String update(User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "edit";
        } else {
            appService.updateUser(user);
            return "redirect:/";
        }
    }
}
