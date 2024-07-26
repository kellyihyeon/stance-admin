package com.github.kellyihyeon.stanceadmin.application.accounttransaction;

import com.github.kellyihyeon.stanceadmin.domain.accounttransaction.AccountTransaction;
import com.github.kellyihyeon.stanceadmin.infrastructure.repository.accounttransaction.AccountTransactionEntity;

public class AccountTransactionMapper {

    public static AccountTransactionEntity toEntity(AccountTransaction accountTransaction) {
        return new AccountTransactionEntity(
                accountTransaction.getAccountId(),
                accountTransaction.getTransactionType(),
                accountTransaction.getTransactionId(),
                accountTransaction.getTransactionSubType(),
                accountTransaction.getAmount(),
                accountTransaction.getBalance(),
                accountTransaction.getCreatedAt(),
                accountTransaction.getCreatorId()
        );
    }
}
