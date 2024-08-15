package com.github.kellyihyeon.stanceadmin.application.transfertransaction.dto;

import com.github.kellyihyeon.stanceadmin.domain.account.Bank;
import com.github.kellyihyeon.stanceadmin.domain.transfertransaction.ExpenseCategory;

import java.math.BigDecimal;
import java.time.LocalDate;

public record TransferCreation(
        ExpenseCategory expenseCategory,
        String recipientName,
        Bank bank,
        String recipientAccountNumber,
        BigDecimal amount,
        LocalDate expenseDate,
        String description
) {
}
