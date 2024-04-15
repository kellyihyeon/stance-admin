package com.github.kellyihyeon.stanceadmin.application.withdraw.dto;

import com.github.kellyihyeon.stanceadmin.domain.accountbook.Bank;
import com.github.kellyihyeon.stanceadmin.domain.withdraw.ExpenseCategory;
import com.github.kellyihyeon.stanceadmin.domain.withdraw.WithdrawCategory;

import java.math.BigDecimal;
import java.time.LocalDate;

public record TransferRequest(
        WithdrawCategory withdrawCategory,
        String recipientName,
        ExpenseCategory expenseCategory,
        LocalDate transactionDate,
        BigDecimal amount,
        Bank bankName,
        String recipientAccountNumber,
        String description
) implements WithdrawRequest {

    @Override
    public WithdrawCategory getWithdrawCategory() {
        return withdrawCategory;
    }

    @Override
    public ExpenseCategory getExpenseCategory() {
        return expenseCategory;
    }

    @Override
    public BigDecimal getAmount() {
        return amount;
    }

    @Override
    public LocalDate getExpenseDate() {
        return transactionDate;
    }

    public String recipientName() {
        return recipientName;
    }

    public Bank bankName() {
        return bankName;
    }

    public String recipientAccountNumber() {
        return recipientAccountNumber;
    }

    public String description() {
        return description;
    }
}
