package com.example.post.controllers;

import com.example.post.dto.Customer;
import com.example.post.services.RegistrationService;
import com.example.post.services.exceptions.CustomerWasRegisteredException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MailController {
    RegistrationService registrationService;

    public MailController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @GetMapping("/")
    public String getMainPage(Model model){
        return "main";
    }

    @GetMapping("/registration")
    public String register(Model model){
        Customer customer = new Customer();
        customer.setPhone("2312");
        model.addAttribute("customer", customer);

        return "registration";
    }

    @GetMapping("/auth")
    public String auth(Model model){
        return "authorization";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute Customer customer) throws CustomerWasRegisteredException {
        registrationService.register(customer);
        return "main";
    }




}
