package com.github.kellyihyeon.stanceadmin.domain.accounttransaction;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class AccountTransactionBuilder {

    private Long id = null;

    private Long accountId = 1L;

    private TransactionType transactionType = TransactionType.DEPOSIT;

    private Long transactionId = 1L;

    private TransactionSubType transactionSubType = TransactionSubType.MEMBERSHIP_FEE;

    private LocalDate transactionDate = LocalDate.now();

    private Double amount = (double) 1000;

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

    public AccountTransactionBuilder amount(Double amount) {
        this.amount = amount;
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
        TransactionIdentity transactionIdentity = TransactionIdentity.create(
                transactionId,
                transactionType,
                transactionSubType,
                transactionDate
        );

        return AccountTransaction.createWithoutId(
                accountId,
                transactionIdentity,
                amount,
                createdAt,
                creatorId
        );
    }
}
