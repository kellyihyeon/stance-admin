package com.github.kellyihyeon.stanceadmin.application.accounttransaction;

import com.github.kellyihyeon.stanceadmin.domain.accounttransaction.AccountTransaction;
import com.github.kellyihyeon.stanceadmin.domain.accounttransaction.TransactionIdentity;
import com.github.kellyihyeon.stanceadmin.infrastructure.entity.accounttransaction.AccountTransactionEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class AccountTransactionMapperImpl implements AccountTransactionMapper {

    @Override
    public AccountTransactionEntity toEntity(AccountTransaction domain) {
        return new AccountTransactionEntity(
                domain.getAccountId(),
                domain.getTransactionType(),
                domain.getTransactionId(),
                domain.getTransactionSubType(),
                domain.getTransactionDate(),
                domain.getAmount(),
                domain.getBalance(),
                domain.getCreatedAt(),
                domain.getCreatorId()
        );
    }

    @Override
    public AccountTransaction toDomain(AccountTransactionEntity entity) {
        if (Objects.isNull(entity)) {
            return null;
        }

        TransactionIdentity transactionIdentity = TransactionIdentity.create(
                entity.getTransactionId(),
                entity.getTransactionType(),
                entity.getTransactionSubType(),
                entity.getTransactionDate()
        );

        return AccountTransaction.createWithId(
                entity.getId(),
                entity.getAccountId(),
                transactionIdentity,
                entity.getAmount(),
                entity.getBalance(),
                entity.getCreatedAt(),
                entity.getCreatorId()
        );
    }

    @Override
    public List<AccountTransaction> toDomains(List<AccountTransactionEntity> transactions) {
        return transactions.stream()
                .map(entity ->
                        AccountTransaction.createWithId(
                                entity.getId(),
                                entity.getAccountId(),
                                TransactionIdentity.create(
                                        entity.getTransactionId(),
                                        entity.getTransactionType(),
                                        entity.getTransactionSubType(),
                                        entity.getTransactionDate()
                                ),
                                entity.getAmount(),
                                entity.getBalance(),
                                entity.getCreatedAt(),
                                entity.getCreatorId()
                        )

                )
                .collect(Collectors.toList());
    }

}
