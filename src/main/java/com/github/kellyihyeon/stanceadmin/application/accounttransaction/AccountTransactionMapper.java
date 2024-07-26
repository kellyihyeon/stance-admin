package com.github.kellyihyeon.stanceadmin.application.accounttransaction;

import com.github.kellyihyeon.stanceadmin.domain.accounttransaction.AccountTransaction;
import com.github.kellyihyeon.stanceadmin.infrastructure.repository.accounttransaction.AccountTransactionEntity;

import java.util.Objects;

public class AccountTransactionMapper {

    public static AccountTransactionEntity toEntity(AccountTransaction domain) {
        return new AccountTransactionEntity(
                domain.getAccountId(),
                domain.getTransactionType(),
                domain.getTransactionId(),
                domain.getTransactionSubType(),
                domain.getAmount(),
                domain.getBalance(),
                domain.getCreatedAt(),
                domain.getCreatorId()
        );
    }

    public static AccountTransaction toDomain(AccountTransactionEntity entity) {
        if (Objects.isNull(entity)) {
            return null;
        }

        return AccountTransaction.create(
                entity.getId(),
                entity.getAccountId(),
                entity.getTransactionType(),
                entity.getTransactionId(),
                entity.getTransactionSubType(),
                entity.getAmount(),
                entity.getBalance(),
                entity.getCreatedAt(),
                entity.getCreatorId()
        );
    }
}
