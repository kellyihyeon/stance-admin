package com.github.kellyihyeon.stanceadmin.application.bankdeposit;

import com.github.kellyihyeon.stanceadmin.application.bankdeposit.dto.BankDepositCreation;
import com.github.kellyihyeon.stanceadmin.domain.bankdeposit.BankDepositTransaction;
import com.github.kellyihyeon.stanceadmin.infrastructure.repository.bankdeposit.BankDepositTransactionEntity;

import java.time.LocalDateTime;

public interface BankDepositTransactionMapper {

    BankDepositTransaction toDomain(BankDepositCreation serviceDto, Long loggedInId, LocalDateTime now);

    BankDepositTransactionEntity toEntity(BankDepositTransaction domain);

    BankDepositTransaction toDomain(BankDepositTransactionEntity entity);

}
