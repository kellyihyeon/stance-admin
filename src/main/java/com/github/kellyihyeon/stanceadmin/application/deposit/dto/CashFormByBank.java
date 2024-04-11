package com.github.kellyihyeon.stanceadmin.application.deposit.dto;

import com.github.kellyihyeon.stanceadmin.domain.deposit.Deposit;
import com.github.kellyihyeon.stanceadmin.domain.deposit.DepositCategory;

public record CashFormByBank(

        DepositCategory depositCategory,
        Long amount,
        String depositDate,
        String depositor,
        String description
) {

    public Deposit toEntity() {
        return Deposit.builder()
                .depositCategory(depositCategory)
                .amount(amount)
                .depositDate(depositDate)
                .depositor(depositor)
                .description(description)
                .build();
    }
}
