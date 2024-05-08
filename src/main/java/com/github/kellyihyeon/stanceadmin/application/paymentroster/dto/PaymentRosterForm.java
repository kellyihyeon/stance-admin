package com.github.kellyihyeon.stanceadmin.application.paymentroster.dto;

import com.github.kellyihyeon.stanceadmin.domain.deposit.DepositCategory;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record PaymentRosterForm(
        DepositCategory category,
        List<Long> memberIds,
        LocalDate applicationDate,
        LocalDate dueDate,
        BigDecimal amount
) { }
