package com.github.kellyihyeon.stanceadmin.application.bankdeposit.dto;

import com.github.kellyihyeon.stanceadmin.domain.bankdeposit.BankDepositTransaction;
import com.github.kellyihyeon.stanceadmin.domain.bankdeposit.DepositType;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class BankDepositCreation {

    public static BankDepositCreation create(DepositType type, String depositorSource, Double amount, LocalDate depositDate, String description) {
        return null;
    }

    public BankDepositTransaction toDomain(Long loggedInId, LocalDateTime now) {
        return null;
    }
}
