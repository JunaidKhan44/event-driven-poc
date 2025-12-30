package com.jk.mail.repository;

import com.jk.mail.domain.EmailRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EmailRequestRepository
        extends JpaRepository<EmailRequest, UUID> {
}
