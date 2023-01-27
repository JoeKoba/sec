package com.example.sec.controller;

import com.example.sec.service.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class AdminController {

    private final AppService appService;

    @Autowired
    public AdminController(AppService appService) {
        this.appService = appService;
    }

    @GetMapping("/")
    public String users(Model model) {
        model.addAttribute("users", appService.findAllUsers());
        return "users";
    }

    @GetMapping("/admin")
    public String adminPage() {
        return "admin";
    }

    @GetMapping("/admin/users")
    public String showUserList(Model model) {
        model.addAttribute("users", appService.findAllUsers());
        return "user-list";
    }

    @GetMapping("/user")
    public String userPage() {
        return "user";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }
}
