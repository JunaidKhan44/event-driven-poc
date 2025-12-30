package com.jk.mail.controller;

import com.jk.mail.domain.EmailRequest;
import com.jk.mail.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/emails")
@RequiredArgsConstructor
public class EmailController {

    private final EmailService emailService;

    @PostMapping
    public ResponseEntity<String> sendEmail(
            @RequestBody EmailRequest req) throws Exception {

        emailService.requestEmail(req);
        return ResponseEntity.ok("Email request accepted");
    }
}
