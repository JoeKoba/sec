//package com.example.sec.controller;
//
//import com.example.sec.model.User;
//import org.springframework.stereotype.Controller;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import javax.validation.Valid;
//
//@Controller
//@RequestMapping("/auth")
//public class AuthController {
//
//
//    @GetMapping("/index")
//    public String index() {
//        return "auth/index";
//    }
//
//    @GetMapping("/login")
//    public String loginPage() {
//        return "login";
//    }
//
//    @GetMapping("/registration")
//    public String registrationPage(@ModelAttribute("person") User person) {
//        return "registration";
//    }
//
//    @PostMapping("/registration")
//    public String performRegistration(@ModelAttribute("person") @Valid User person,
//                                      BindingResult bindingResult) {
//        if (bindingResult.hasErrors()) {
//            return "registration";
//        }
//
//        return "redirect:/auth/login";
//    }
//}
