package com.github.kellyihyeon.stanceadmin.application.account.dto;

import com.github.kellyihyeon.stanceadmin.domain.account.AccountStatus;
import com.github.kellyihyeon.stanceadmin.domain.account.Bank;

import java.time.LocalDateTime;

public record AccountCreation(
        boolean isDefault,
        String accountNickname,
        String accountHolder,
        String accountNumber,
        Bank bank,
        LocalDateTime usageStartDate,
        LocalDateTime usageEndDate,
        AccountStatus status
) {
}
