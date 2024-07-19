package com.github.kellyihyeon.stanceadmin.application.event.dto;

import com.github.kellyihyeon.stanceadmin.domain.event.EventItem;
import com.github.kellyihyeon.stanceadmin.domain.event.EventStatus;

import java.time.LocalDate;

public record EventCreation(
        EventItem eventItem,
        Double amount,
        LocalDate dueDate,
        String description,
        EventStatus status) {

    public EventCreation(EventItem eventItem, Double amount, LocalDate dueDate, String description, EventStatus status) {
        this.eventItem = eventItem;
        this.amount = amount;
        this.dueDate = dueDate;
        this.description = description;
        this.status = status;
    }
}
