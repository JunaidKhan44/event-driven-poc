package com.jk.mail.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EmailRequest {

    @Id
    private UUID id;

    private String toEmail;
    private String subject;
    private String body;
    private String priority; // HIGH, MEDIUM, LOW

    // getters & setters
}
