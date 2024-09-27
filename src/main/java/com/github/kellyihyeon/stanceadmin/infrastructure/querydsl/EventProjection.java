package com.github.kellyihyeon.stanceadmin.infrastructure.querydsl;

import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
public class EventProjection {
    private Long id;

    private String eventName;

    private String eventMemo;

    private BigDecimal amount;

    private LocalDate dueDate;

    private LocalDateTime createdAt;

    private String creatorName;

    public EventProjection(Long id, String eventName, String eventMemo, BigDecimal amount, LocalDate dueDate, LocalDateTime createdAt, String creatorName) {
        this.id = id;
        this.eventName = eventName;
        this.eventMemo = eventMemo;
        this.amount = amount;
        this.dueDate = dueDate;
        this.createdAt = createdAt;
        this.creatorName = creatorName;
    }
}
