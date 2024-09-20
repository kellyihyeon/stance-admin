package com.github.kellyihyeon.stanceadmin.domain.event;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Event {

    private Long id;

    private EventItem eventItem;

    private Double amount;

    private LocalDate dueDate;

    private String description;

    private EventStatus status;

    private Long creatorId;

    private LocalDateTime createdAt;

    public Event(Long id, EventItem eventItem, Double amount, LocalDate dueDate, String description, EventStatus status, LocalDateTime createdAt, Long creatorId) {
        this.id = id;
        this.eventItem = eventItem;
        this.amount = amount;
        this.dueDate = dueDate;
        this.description = description;
        this.status = status;
        this.creatorId = creatorId;
        this.createdAt = createdAt;
    }

    public static Event createWithId(Long id, EventItem eventItem, Double amount, LocalDate dueDate, String description, EventStatus status, Long creatorId,  LocalDateTime createdAt) {
        Objects.requireNonNull(eventItem, "eventItem 은 null 이어서는 안됩니다.");
        Objects.requireNonNull(amount, "amount 은 null 이어서는 안됩니다.");
        Objects.requireNonNull(dueDate, "dueDate 는 null 이어서는 안됩니다.");
        Objects.requireNonNull(status, "status 는 null 이어서는 안됩니다.");
        Objects.requireNonNull(createdAt, "createdAt 은 null 이어서는 안됩니다.");
        Objects.requireNonNull(creatorId, "creatorId 는 null 이어서는 안됩니다.");

        return new Event(id, eventItem, amount, dueDate, description, status, createdAt, creatorId);
    }
}
