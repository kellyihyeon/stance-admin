package com.github.kellyihyeon.stanceadmin.application.accounttransaction.dto;

import com.github.kellyihyeon.stanceadmin.domain.eventdeposittransaction.EventDepositTransaction;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class EventDepositCreation {

    private final Long eventId;
    private final List<Long> depositorIds;
    private final Double amount;
    private final LocalDate depositDate;
    private final String description;

    private EventDepositCreation(Long eventId, List<Long> depositorIds, Double amount, LocalDate depositDate, String description) {
        this.eventId = eventId;
        this.depositorIds = depositorIds;
        this.amount = amount;
        this.depositDate = depositDate;
        this.description = description;
    }

    public static EventDepositCreation create(Long eventId, List<Long> depositorIds, Double amount, LocalDate depositDate, String description) {
        Objects.requireNonNull(eventId, "eventId 는 null 이어선 안됩니다.");
        Objects.requireNonNull(depositorIds, "depositorIds 는 null 이어선 안됩니다.");
        if (depositorIds.isEmpty()) {
            throw new IllegalArgumentException("입금자 아이디가 없어요.");
        }
        Objects.requireNonNull(amount, "amount 는 null 이어선 안됩니다.");
        Objects.requireNonNull(depositDate, "depositDate 는 null 이어선 안됩니다.");

        return new EventDepositCreation(eventId, depositorIds, amount, depositDate, description);
    }

    public List<EventDepositTransaction> toDomains(Long loggedInId, LocalDateTime now) {
        List<EventDepositTransaction> transactions = new ArrayList<>();

        for (Long depositorId : depositorIds) {
            transactions.add(EventDepositTransaction.create(
                    null,
                    this.eventId,
                    depositorId,
                    this.amount,
                    this.depositDate,
                    this.description,
                    loggedInId,
                    now
            ));
        }

        return transactions;
    }
}
