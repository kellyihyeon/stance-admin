package com.github.kellyihyeon.stanceadmin.application.deposit.dto;

import com.github.kellyihyeon.stanceadmin.domain.deposit.DepositCategory;

import java.math.BigDecimal;

public interface DepositCategoryHistoryProjection {

    Long getId();
    DepositCategory getCategory();
    BigDecimal getAmount();
}
