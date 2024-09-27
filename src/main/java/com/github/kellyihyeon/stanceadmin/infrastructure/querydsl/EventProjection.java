package com.github.kellyihyeon.stanceadmin.infrastructure.querydsl;

import com.github.kellyihyeon.stanceadmin.domain.event.EventItem;
import com.github.kellyihyeon.stanceadmin.domain.event.EventStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record EventProjection(
        Long id,
        EventItem eventItem,
        String eventMemo,
        Double amount,
        LocalDate dueDate,
        EventStatus status,
        LocalDateTime createdAt,
        String creatorName
) {
}