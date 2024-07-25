package com.github.kellyihyeon.stanceadmin.application.bankdeposit;

import com.github.kellyihyeon.stanceadmin.domain.bankdeposit.BankDepositTransaction;
import com.github.kellyihyeon.stanceadmin.infrastructure.repository.bankdeposit.BankDepositTransactionEntity;

import java.time.LocalDateTime;

public class BankDepositTransactionMapper {

    public static BankDepositTransactionEntity toEntity(BankDepositTransaction domain) {
        return null;
    }

    public static BankDepositTransaction toDomain(Long loggedInId, LocalDateTime now) {
        return null;
    }
}
