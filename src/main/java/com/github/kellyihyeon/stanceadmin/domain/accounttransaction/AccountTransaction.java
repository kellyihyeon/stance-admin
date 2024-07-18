package com.github.kellyihyeon.stanceadmin.domain.accounttransaction;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AccountTransaction {

    private Long id;

    private Long accountId;

    private TransactionType transactionType;

    private Long transactionId;

    private TransactionSubType transactionSubType;

    private LocalDateTime createdAt;

    private Long creatorId;

    public AccountTransaction(Long id, Long accountId, TransactionType transactionType, Long transactionId, TransactionSubType transactionSubType, LocalDateTime createdAt, Long creatorId) {
        this.id = id;
        this.accountId = accountId;
        this.transactionType = transactionType;
        this.transactionId = transactionId;
        this.transactionSubType = transactionSubType;
        this.createdAt = createdAt;
        this.creatorId = creatorId;
    }
}
