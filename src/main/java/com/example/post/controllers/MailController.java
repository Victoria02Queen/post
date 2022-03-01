package com.example.post.controllers;

import com.example.post.dto.Customer;
import com.example.post.dto.Package;
import com.example.post.services.AuthService;
import com.example.post.services.PackageService;
import com.example.post.services.exceptions.CustomerWasRegisteredException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class MailController {
    AuthService authService;
    PackageService packageService;

    public MailController(AuthService authService, PackageService packageService) {
        this.packageService = packageService;
        this.authService = authService;
    }

    @GetMapping("/")
    public String getMainPage(Model model){
        return "main";
    }

    @GetMapping("/registration")
    public String register(Model model){
        Customer customer = new Customer();
        model.addAttribute("customer", customer);

        return "registration";
    }

    @GetMapping("/auth")
    public String auth(Model model, HttpSession httpSession){
        Customer customer = new Customer();
        model.addAttribute("customer", customer);

        return "authorization";
    }

    @PostMapping("/customer")
    public String getCustomerPage(Model model, @RequestParam String phone, @RequestParam String password){
        Customer customer = authService.auth(phone, password);

        if (customer == null){
            throw new RuntimeException("Auth was failed");
        }

        List<Package> packageList = packageService.getPackagesForCustomer(customer);

        model.addAttribute("customer", customer);
        model.addAttribute("packages", packageList.toString());

        return "customerPage";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute Customer customer) throws CustomerWasRegisteredException {
        authService.register(customer);
        return "main";
    }




}
