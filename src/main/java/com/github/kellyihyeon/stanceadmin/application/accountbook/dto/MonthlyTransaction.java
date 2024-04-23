package com.github.kellyihyeon.stanceadmin.application.accountbook.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class MonthlyTransaction {

    private BigDecimal depositAmount;

    private BigDecimal withdrawalAmount;


    public void plusDepositAmount(BigDecimal amount) {
        this.depositAmount = this.depositAmount.add(amount);
    }

    public void plusWithdrawalAmount(BigDecimal amount) {
        this.withdrawalAmount = this.withdrawalAmount.add(amount);
    }
}
