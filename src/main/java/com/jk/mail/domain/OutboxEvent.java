package com.jk.mail.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "outbox_events")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OutboxEvent {

    @Id
    private UUID id;

    private UUID aggregateId;
    private String eventType;
    private String priority;

    @Column(columnDefinition = "json")
    private String payload;

    private String status;
}

