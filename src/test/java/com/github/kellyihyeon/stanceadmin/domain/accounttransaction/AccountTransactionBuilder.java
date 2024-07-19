package com.github.kellyihyeon.stanceadmin.domain.accounttransaction;

import java.time.LocalDateTime;

public class AccountTransactionBuilder {

    private Long id = null;

    private Long accountId = 1L;

    private TransactionType transactionType = TransactionType.DEPOSIT;

    private Long transactionId = 1L;

    private TransactionSubType transactionSubType = TransactionSubType.MEMBERSHIP_FEE;

    private LocalDateTime createdAt = LocalDateTime.now();

    private Long creatorId = 999L;

    public static AccountTransactionBuilder builder() {
        return new AccountTransactionBuilder();
    }

    public AccountTransactionBuilder accountId(Long accountId) {
        this.accountId = accountId;
        return this;
    }

    public AccountTransactionBuilder transactionId(Long transactionId) {
        this.transactionId = transactionId;
        return this;
    }

    public AccountTransactionBuilder transactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
        return this;
    }

    public AccountTransactionBuilder transactionSubType(TransactionSubType transactionSubType) {
        this.transactionSubType = transactionSubType;
        return this;
    }

    public AccountTransactionBuilder createdAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public AccountTransactionBuilder creatorId(Long creatorId) {
        this.creatorId = creatorId;
        return this;
    }

    public AccountTransaction build() {
        return AccountTransaction.create(
                id,
                accountId,
                transactionType,
                transactionId,
                transactionSubType,
                createdAt,
                creatorId
        );
    }
}
