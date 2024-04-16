package com.github.kellyihyeon.stanceadmin.application.withdraw.dto;

import com.github.kellyihyeon.stanceadmin.domain.withdraw.ExpenseCategory;
import com.github.kellyihyeon.stanceadmin.domain.withdraw.WithdrawCategory;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public record CardPaymentRequest(
        @NotNull WithdrawCategory withdrawCategory,
        @NotNull ExpenseCategory expenseCategory,
        @NotNull BigDecimal amount,
        @NotNull LocalDate expenseDate,
        @NotNull String cardPayer,
        @NotNull String cardUsageLocation,
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
        return expenseDate;
    }

    @Override
    public String getDescription() {
        return description;
    }
}
