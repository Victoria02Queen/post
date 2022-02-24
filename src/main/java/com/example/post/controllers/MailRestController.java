package com.example.post.controllers;

import com.example.post.dao.PackageDao;
import com.example.post.services.RegistrationService;
import org.postgresql.shaded.com.ongres.scram.common.bouncycastle.pbkdf2.Pack;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MailRestController {
    RegistrationService registrationService;

    public MailRestController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }


}
