package com.github.kellyihyeon.stanceadmin.domain.eventdeposittransaction;

import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
public class EventDepositTransaction {


    public static EventDepositTransaction create(Long eventId, Long applicantId, Double amount, LocalDate depositDate, String description, Long creatorId, LocalDateTime createdAt) {
        return null;
    }
}
