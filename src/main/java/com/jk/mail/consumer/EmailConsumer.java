package com.jk.mail.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jk.mail.domain.EmailRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmailConsumer {

    private final JavaMailSender mailSender;
    private final ObjectMapper objectMapper;

    @KafkaListener(topics = "email.high", groupId = "email-high")
    public void consumeHigh(String msg) throws Exception {
        sendEmail(msg, "HIGH");
    }

    @KafkaListener(topics = "email.medium", groupId = "email-medium")
    public void consumeMedium(String msg) throws Exception {
        sendEmail(msg, "MEDIUM");
    }

    @KafkaListener(topics = "email.low", groupId = "email-low")
    public void consumeLow(String msg) throws Exception {
        sendEmail(msg, "LOW");
    }

    private void sendEmail(String payload, String priority) throws Exception {

        EmailRequest req =
                objectMapper.readValue(payload, EmailRequest.class);

        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(req.getToEmail());
        mail.setSubject("[" + priority + "] " + req.getSubject());
        mail.setText(req.getBody());

        mailSender.send(mail);
    }
}

