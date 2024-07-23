package com.github.kellyihyeon.stanceadmin.domain.eventdeposittransaction;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class EventDepositTransactionBuilder {

    private Long eventId = 1L;
    private Long applicantId = 2L;
    private Double amount = (double) 70000L;
    private LocalDate depositDate = LocalDate.now();
    private String description = "아마추어 대회 2회";
    private Long creatorId = 999L;
    private LocalDateTime createdAt = LocalDateTime.now();


    public static EventDepositTransactionBuilder builder() {
        return new EventDepositTransactionBuilder();
    }


    public EventDepositTransactionBuilder eventId(Long eventId) {
        this.eventId = eventId;
        return this;
    }

    public EventDepositTransactionBuilder applicantId(Long applicantId) {
        this.applicantId = applicantId;
        return this;
    }

    public EventDepositTransactionBuilder amount(Double amount) {
        this.amount = amount;
        return this;
    }

    public EventDepositTransactionBuilder depositDate(LocalDate depositDate) {
        this.depositDate = depositDate;
        return this;
    }


    public EventDepositTransactionBuilder description(String description) {
        this.description = description;
        return this;
    }

    public EventDepositTransactionBuilder createdAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public EventDepositTransactionBuilder creatorId(Long creatorId) {
        this.creatorId = creatorId;
        return this;
    }

    public EventDepositTransaction build() {
        return EventDepositTransaction.create(
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
