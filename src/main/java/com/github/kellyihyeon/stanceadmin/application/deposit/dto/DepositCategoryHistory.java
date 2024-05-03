package com.github.kellyihyeon.stanceadmin.application.deposit.dto;

import com.github.kellyihyeon.stanceadmin.domain.deposit.DepositCategory;

import java.math.BigDecimal;

public record DepositCategoryHistory(
        Long id,
        DepositCategory category,
        BigDecimal amount
) { }
