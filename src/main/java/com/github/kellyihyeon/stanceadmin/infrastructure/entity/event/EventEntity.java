package com.github.kellyihyeon.stanceadmin.infrastructure.entity.event;

import com.github.kellyihyeon.stanceadmin.domain.event.EventItem;
import com.github.kellyihyeon.stanceadmin.domain.event.EventStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "events")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EventEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "event_item", nullable = false)
    private EventItem eventItem;

    @Column(name = "amount", nullable = false)
    private Double amount;

    @Column(name = "due_date", nullable = false)
    private LocalDate dueDate;

    @Column(name = "description", nullable = false)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private EventStatus status;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "creator_id", nullable = false)
    private Long creatorId;

    public EventEntity(EventItem eventItem, Double amount, LocalDate dueDate, String description, EventStatus status, LocalDateTime createdAt, Long creatorId) {
        this.eventItem = eventItem;
        this.amount = amount;
        this.dueDate = dueDate;
        this.description = description;
        this.status = status;
        this.createdAt = createdAt;
        this.creatorId = creatorId;
    }
}
