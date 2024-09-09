package com.github.kellyihyeon.stanceadmin.application.cardpayment.dto;

import com.github.kellyihyeon.stanceadmin.domain.transfertransaction.ExpenseCategory;

import java.math.BigDecimal;
import java.time.LocalDate;

public record CardPaymentCreation(
        Long cardHolderId,
        ExpenseCategory expenseCategory,
        String cardUsageLocation,
        BigDecimal amount,
        LocalDate expenseDate,
        String description
) { }
