package com.github.kellyihyeon.stanceadmin.application.account.dto;

import com.github.kellyihyeon.stanceadmin.domain.account.AccountStatus;
import com.github.kellyihyeon.stanceadmin.domain.account.Bank;

import java.time.LocalDate;

public record AccountCreation(
        Boolean isDefault,
        String accountNickname,
        String accountHolder,
        String accountNumber,
        Bank bank,
        LocalDate usageStartDate,
        AccountStatus status
) {
}
