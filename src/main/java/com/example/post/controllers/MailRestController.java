package com.example.post.controllers;

import com.example.post.services.AuthService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MailRestController {
    AuthService authService;

    public MailRestController(AuthService authService) {
        this.authService = authService;
    }


}
