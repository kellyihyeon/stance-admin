package com.github.kellyihyeon.stanceadmin.domain.eventdeposittransaction;

import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
public class EventDepositTransaction {


    public static EventDepositTransaction create(Long eventId, Long applicantId, Double amount, LocalDate depositDate, String description, Long creatorId, LocalDateTime createdAt) {
        Objects.requireNonNull(eventId, "eventId 는 null 이어선 안됩니다.");
        Objects.requireNonNull(applicantId, "applicantId 는 null 이어선 안됩니다.");
        Objects.requireNonNull(amount, "amount 는 null 이어선 안됩니다.");
        Objects.requireNonNull(depositDate, "depositDate 는 null 이어선 안됩니다.");
        Objects.requireNonNull(creatorId, "creatorId 는 null 이어선 안됩니다.");
        Objects.requireNonNull(createdAt, "createdAt 은 null 이어선 안됩니다.");
        return null;
    }
}
