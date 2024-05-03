package com.github.kellyihyeon.stanceadmin.application.deposit.dto;

import java.math.BigDecimal;
import java.time.Year;
import java.util.List;

public record TopDepositCategoriesResponse(
        Year year,
        Integer month,
        BigDecimal total,
        List<TopDeposit> history
) { }
