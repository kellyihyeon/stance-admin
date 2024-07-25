package com.github.kellyihyeon.stanceadmin.application.bankdeposit.dto;

import com.github.kellyihyeon.stanceadmin.domain.bankdeposit.DepositType;

import java.time.LocalDate;
import java.util.Objects;

public class BankDepositCreation {

    private DepositType type;
    private String depositorSource;
    private Double amount;
    private LocalDate depositDate;
    private String description;

    public BankDepositCreation(DepositType type, String depositorSource, Double amount, LocalDate depositDate, String description) {
        this.type = type;
        this.depositorSource = depositorSource;
        this.amount = amount;
        this.depositDate = depositDate;
        this.description = description;
    }


    public static BankDepositCreation create(DepositType type, String depositorSource, Double amount, LocalDate depositDate, String description) {
        Objects.requireNonNull(type, "type 이 null 이어선 안됩니다.");
        Objects.requireNonNull(depositorSource, "depositorSource 가 null 이어선 안됩니다.");
        Objects.requireNonNull(amount, "amount 가 null 이어선 안됩니다.");
        Objects.requireNonNull(depositDate, "depositDate 가 null 이어선 안됩니다.");

        return new BankDepositCreation(type, depositorSource, amount, depositDate, description);
    }
}
