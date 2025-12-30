package com.jk.mail.service;

import com.jk.mail.domain.OutboxEvent;
import com.jk.mail.repository.OutboxEventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class OutboxPublisher {

    private final OutboxEventRepository outboxRepo;
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Scheduled(fixedDelay = 5000)
    public void publishEvents() {

        List<OutboxEvent> events =
                outboxRepo.findByStatus("NEW");

        for (OutboxEvent e : events) {

            String topic = "email." + e.getPriority().toLowerCase();
            kafkaTemplate.send(topic, e.getPayload());

            e.setStatus("SENT");
            outboxRepo.save(e);
        }
    }
}
