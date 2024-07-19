package com.github.kellyihyeon.stanceadmin.domain.event;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
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

    public Event(Long id, EventItem eventItem, Double amount, LocalDate dueDate, String description, EventStatus status) {
        this.id = id;
        this.eventItem = eventItem;
        this.amount = amount;
        this.dueDate = dueDate;
        this.description = description;
        this.status = status;
    }

    public Event create(Long id, EventItem eventItem, Double amount, LocalDate dueDate, String description, EventStatus status) {
        Objects.requireNonNull(eventItem, "eventItem 은 null 이어서는 안됩니다.");
        Objects.requireNonNull(amount, "amount 은 null 이어서는 안됩니다.");
        Objects.requireNonNull(dueDate, "dueDate 는 null 이어서는 안됩니다.");
        Objects.requireNonNull(status, "status 는 null 이어서는 안됩니다.");

        return new Event(id, eventItem, amount, dueDate, description, status);
    }
}
