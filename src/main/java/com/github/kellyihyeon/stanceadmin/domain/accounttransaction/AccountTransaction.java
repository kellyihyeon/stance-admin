package com.github.kellyihyeon.stanceadmin.domain.accounttransaction;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AccountTransaction {

    private Long id;

    private Long accountId;

    private TransactionType transactionType;

    private Long transactionId;

    private TransactionSubType transactionSubType;

    private Double amount;

    private Double balance;

    private LocalDateTime createdAt;

    private Long creatorId;


    private AccountTransaction(Long id, Long accountId, TransactionType transactionType, Long transactionId, TransactionSubType transactionSubType, Double amount, Double balance, LocalDateTime createdAt, Long creatorId) {
        this.id = id;
        this.accountId = accountId;
        this.transactionType = transactionType;
        this.transactionId = transactionId;
        this.transactionSubType = transactionSubType;
        this.amount = amount;
        this.balance = balance;
        this.createdAt = createdAt;
        this.creatorId = creatorId;
    }

    private AccountTransaction(Long id, Long accountId, TransactionType transactionType, Long transactionId, TransactionSubType transactionSubType, LocalDateTime createdAt, Long creatorId) {
        this.id = id;
        this.accountId = accountId;
        this.transactionType = transactionType;
        this.transactionId = transactionId;
        this.transactionSubType = transactionSubType;
        this.createdAt = createdAt;
        this.creatorId = creatorId;
    }

    private AccountTransaction(Long accountId, TransactionType transactionType, Long transactionId, TransactionSubType transactionSubType, Double amount, LocalDateTime createdAt, Long creatorId) {
        this.accountId = accountId;
        this.transactionType = transactionType;
        this.transactionId = transactionId;
        this.transactionSubType = transactionSubType;
        this.amount = amount;
        this.createdAt = createdAt;
        this.creatorId = creatorId;
    }

    public static AccountTransaction create(Long id, Long accountId, TransactionType transactionType, Long transactionId, TransactionSubType transactionSubType, LocalDateTime createdAt, Long creatorId) {
        Objects.requireNonNull(accountId, "accountId 가 null 이어서는 안됩니다.");
        Objects.requireNonNull(transactionType, "transactionType 이 null 이어서는 안됩니다.");
        Objects.requireNonNull(transactionId, "transactionId 가 null 이어서는 안됩니다.");
        Objects.requireNonNull(transactionSubType, "transactionSubType 이 null 이어서는 안됩니다.");
        Objects.requireNonNull(createdAt, "createdAt 이 null 이어서는 안됩니다.");
        Objects.requireNonNull(creatorId, "creatorId 가 null 이어서는 안됩니다.");

        return new AccountTransaction(id, accountId, transactionType, transactionId, transactionSubType, createdAt, creatorId);
    }

    public static AccountTransaction create(Long accountId, TransactionType transactionType, Long transactionId, TransactionSubType transactionSubType, Double amount, LocalDateTime createdAt, Long creatorId) {
        Objects.requireNonNull(accountId, "accountId 가 null 이어서는 안됩니다.");
        Objects.requireNonNull(transactionType, "transactionType 이 null 이어서는 안됩니다.");
        Objects.requireNonNull(transactionId, "transactionId 가 null 이어서는 안됩니다.");
        Objects.requireNonNull(transactionSubType, "transactionSubType 이 null 이어서는 안됩니다.");
        Objects.requireNonNull(amount, "amount 가 null 이어서는 안됩니다.");
        Objects.requireNonNull(createdAt, "createdAt 이 null 이어서는 안됩니다.");
        Objects.requireNonNull(creatorId, "creatorId 가 null 이어서는 안됩니다.");

        return new AccountTransaction(accountId, transactionType, transactionId, transactionSubType, amount, createdAt, creatorId);
    }

    public static AccountTransaction create(Long id, Long accountId, TransactionType transactionType, Long transactionId, TransactionSubType transactionSubType, Double amount, Double balance, LocalDateTime createdAt, Long creatorId) {
        Objects.requireNonNull(id, "id 가 null 이어서는 안됩니다.");
        Objects.requireNonNull(accountId, "accountId 가 null 이어서는 안됩니다.");
        Objects.requireNonNull(transactionType, "transactionType 이 null 이어서는 안됩니다.");
        Objects.requireNonNull(transactionId, "transactionId 가 null 이어서는 안됩니다.");
        Objects.requireNonNull(transactionSubType, "transactionSubType 이 null 이어서는 안됩니다.");
        Objects.requireNonNull(amount, "amount 가 null 이어서는 안됩니다.");
        Objects.requireNonNull(balance, "balance 가 null 이어서는 안됩니다.");
        Objects.requireNonNull(createdAt, "createdAt 이 null 이어서는 안됩니다.");
        Objects.requireNonNull(creatorId, "creatorId 가 null 이어서는 안됩니다.");

        return new AccountTransaction(id, accountId, transactionType, transactionId, transactionSubType, amount, balance, createdAt, creatorId);
    }

    public Double addAmountToBalance(Double latestBalance) {
        this.balance = latestBalance + this.amount;
        return this.balance;
    }

    public Double subtractAmountFromBalance(Double latestBalance) {
        this.balance = latestBalance - this.amount;
        return this.balance;
    }
}
