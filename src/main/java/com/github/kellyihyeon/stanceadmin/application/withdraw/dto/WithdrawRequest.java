package com.github.kellyihyeon.stanceadmin.application.withdraw.dto;

import com.github.kellyihyeon.stanceadmin.domain.withdraw.ExpenseCategory;
import com.github.kellyihyeon.stanceadmin.domain.withdraw.WithdrawCategory;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface WithdrawRequest {

    WithdrawCategory getWithdrawCategory();

    ExpenseCategory getExpenseCategory();

    BigDecimal getAmount();

    LocalDate getExpenseDate();

    String getDescription();

}
