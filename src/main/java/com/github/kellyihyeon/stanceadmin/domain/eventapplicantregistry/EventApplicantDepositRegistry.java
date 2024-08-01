package com.github.kellyihyeon.stanceadmin.domain.eventapplicantregistry;

import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.Objects;

@ToString
@Getter
public class EventApplicantDepositRegistry {

    private String eventDescription;

    private LocalDate dueDate;

    private String memberName;

    private Double amount;

    private DepositStatus depositStatus;

    private EventApplicantDepositRegistry(String eventDescription, LocalDate dueDate, String memberName, Double amount, DepositStatus depositStatus) {
        this.eventDescription = eventDescription;
        this.dueDate = dueDate;
        this.memberName = memberName;
        this.amount = amount;
        this.depositStatus = depositStatus;
    }

    public static EventApplicantDepositRegistry create(String eventDescription, LocalDate dueDate, String memberName, Double amount, DepositStatus depositStatus) {
        Objects.requireNonNull(dueDate);
        Objects.requireNonNull(memberName);
        Objects.requireNonNull(amount);
        Objects.requireNonNull(depositStatus);

        return new EventApplicantDepositRegistry(
                eventDescription,
                dueDate,
                memberName,
                amount,
                depositStatus
        );
    }
}
