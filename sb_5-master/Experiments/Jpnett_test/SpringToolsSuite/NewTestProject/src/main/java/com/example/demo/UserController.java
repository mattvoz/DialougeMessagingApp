package com.example.demo;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    @GetMapping("/addUser")
    public String sendForm(User user) {
        return "userForm";
    }

    @PostMapping("/addUser")
    public String postForm(User user) {
        return "userData";
    }
}

