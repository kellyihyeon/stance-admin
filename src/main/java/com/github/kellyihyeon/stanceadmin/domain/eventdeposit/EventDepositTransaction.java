package com.github.kellyihyeon.stanceadmin.domain.eventdeposit;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EventDepositTransaction {

    private Long id;

    private Long eventId;

    private Long applicantId;

    private Double amount;

    private LocalDate depositDate;

    private String description;

    private Long creatorId;

    private LocalDateTime createdAt;

    private Long updaterId;

    private LocalDateTime updatedAt;


    public EventDepositTransaction(Long id, Long eventId, Long applicantId, Double amount, LocalDate depositDate, String description, Long creatorId, LocalDateTime createdAt) {
        this.id = id;
        this.eventId = eventId;
        this.applicantId = applicantId;
        this.amount = amount;
        this.depositDate = depositDate;
        this.description = description;
        this.creatorId = creatorId;
        this.createdAt = createdAt;
    }

    public static EventDepositTransaction create(Long id, Long eventId, Long applicantId, Double amount, LocalDate depositDate, String description, Long creatorId, LocalDateTime createdAt) {
        Objects.requireNonNull(eventId, "eventId 는 null 이어선 안됩니다.");
        Objects.requireNonNull(applicantId, "applicantId 는 null 이어선 안됩니다.");
        Objects.requireNonNull(amount, "amount 는 null 이어선 안됩니다.");
        Objects.requireNonNull(depositDate, "depositDate 는 null 이어선 안됩니다.");
        Objects.requireNonNull(creatorId, "creatorId 는 null 이어선 안됩니다.");
        Objects.requireNonNull(createdAt, "createdAt 은 null 이어선 안됩니다.");

        return new EventDepositTransaction(
                id,
                eventId,
                applicantId,
                amount,
                depositDate,
                description,
                creatorId,
                createdAt
        );
    }
}