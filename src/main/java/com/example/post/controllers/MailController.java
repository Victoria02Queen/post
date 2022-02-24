package com.example.post.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MailController {

    @GetMapping("/")
    public String getMainPage(Model model){
        return "main";
    }

    @GetMapping("/registration")
    public String register(Model model){
        return "registration";
    }

    @GetMapping("/auth")
    public String auth(Model model){
        return "authorization";
    }


}
