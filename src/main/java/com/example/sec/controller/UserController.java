package com.example.sec.controller;

import com.example.sec.model.User;
import com.example.sec.repository.UserRepository;
import com.example.sec.service.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {

    private final AppService appService;
    private final UserRepository userRepository;

    @Autowired
    public UserController(AppService appService, UserRepository userRepository) {
        this.appService = appService;
        this.userRepository = userRepository;
    }

    @GetMapping("/user")
    public String userPage() {
        return "user";
    }

    @GetMapping("/users")
    public String userList(Model model, User user) {
        model.addAttribute("user", userRepository.findAll());
        return "users";
    }
}
