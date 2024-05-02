package com.github.kellyihyeon.stanceadmin.application.deposit.dto;

import java.math.BigDecimal;

public record TopDeposit(
        Integer rank,
        String depositCategory,
        BigDecimal amount
) { }
