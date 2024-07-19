package com.github.kellyihyeon.stanceadmin.infrastructure.repository.event;

import com.github.kellyihyeon.stanceadmin.domain.event.EventItem;
import com.github.kellyihyeon.stanceadmin.domain.event.EventStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
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

    public EventEntity(EventItem eventItem, Double amount, LocalDate dueDate, String description, EventStatus status) {
        this.eventItem = eventItem;
        this.amount = amount;
        this.dueDate = dueDate;
        this.description = description;
        this.status = status;
    }
}
