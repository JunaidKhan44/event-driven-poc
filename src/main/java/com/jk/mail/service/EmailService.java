package com.jk.mail.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jk.mail.domain.EmailRequest;
import com.jk.mail.domain.OutboxEvent;
import com.jk.mail.repository.EmailRequestRepository;
import com.jk.mail.repository.OutboxEventRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final EmailRequestRepository emailRepo;
    private final OutboxEventRepository outboxRepo;
    private final ObjectMapper objectMapper;

    @Transactional
    public void requestEmail(EmailRequest req) throws Exception {

        req.setId(UUID.randomUUID());
        emailRepo.save(req);

        OutboxEvent event = new OutboxEvent();
        event.setId(UUID.randomUUID());
        event.setAggregateId(req.getId());
        event.setEventType("EMAIL_REQUESTED");
        event.setPriority(req.getPriority());
        event.setPayload(objectMapper.writeValueAsString(req));
        event.setStatus("NEW");

        outboxRepo.save(event);
    }
}
