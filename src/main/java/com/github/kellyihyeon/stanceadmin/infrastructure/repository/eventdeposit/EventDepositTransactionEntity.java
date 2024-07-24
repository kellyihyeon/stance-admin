package com.github.kellyihyeon.stanceadmin.infrastructure.repository.eventdeposit;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "event_deposit_transactions")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EventDepositTransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "event_id", nullable = false)
    private Long eventId;

    @Column(name = "applicant_id", nullable = false)
    private Long applicantId;

    @Column(name = "amount", nullable = false)
    private Double amount;

    @Column(name = "deposit_date", nullable = false)
    private LocalDate depositDate;

    @Column(name = "description")
    private String description;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "creator_id", nullable = false)
    private Long creatorId;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "updater_id")
    private Long updaterId;

    private EventDepositTransactionEntity(Long eventId, Long applicantId, Double amount, LocalDate depositDate, String description, LocalDateTime createdAt, Long creatorId) {
        this.eventId = eventId;
        this.applicantId = applicantId;
        this.amount = amount;
        this.depositDate = depositDate;
        this.description = description;
        this.createdAt = createdAt;
        this.creatorId = creatorId;
    }

    public static EventDepositTransactionEntity create(Long eventId, Long applicantId, Double amount, LocalDate depositDate, String description, LocalDateTime createdAt, Long creatorId) {
        Objects.requireNonNull(eventId, "eventId 는 null 이어선 안됩니다.");
        Objects.requireNonNull(applicantId, "applicantId 는 null 이어선 안됩니다.");
        Objects.requireNonNull(amount, "amount 는 null 이어선 안됩니다.");
        Objects.requireNonNull(depositDate, "depositDate 는 null 이어선 안됩니다.");
        Objects.requireNonNull(createdAt, "createdAt 은 null 이어선 안됩니다.");
        Objects.requireNonNull(creatorId, "creatorId 는 null 이어선 안됩니다.");

        return new EventDepositTransactionEntity(
                eventId,
                applicantId,
                amount,
                depositDate,
                description,
                createdAt,
                creatorId
        );
    }
}
