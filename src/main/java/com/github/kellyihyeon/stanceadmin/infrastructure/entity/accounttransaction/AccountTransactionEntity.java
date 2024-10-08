package com.github.kellyihyeon.stanceadmin.infrastructure.entity.accounttransaction;

import com.github.kellyihyeon.stanceadmin.domain.accounttransaction.*;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.AfterDomainEventPublication;
import org.springframework.data.domain.DomainEvents;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Slf4j
@Entity
@Getter
@ToString
@Table(name = "account_transactions")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AccountTransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "account_id", nullable = false)
    private Long accountId;

    @Column(name = "transaction_date", nullable = false)
    private LocalDate transactionDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "transcation_type", nullable = false)
    private TransactionType transactionType;

    @Column(name = "transaction_id", nullable = false)
    private Long transactionId;

    @Enumerated(EnumType.STRING)
    @Column(name = "transaction_sub_type", nullable = false)
    private TransactionSubType transactionSubType;

    @Column(name = "amount", nullable = false)
    private Double amount;

    @Column(name = "balance", nullable = false)
    private Double balance;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "creator_id", nullable = false)
    private Long creatorId;

    @Transient
    private final List<Object> accountTransactionDomainEvents = new ArrayList<>();

    public AccountTransactionEntity(Long accountId, TransactionType transactionType, Long transactionId, TransactionSubType transactionSubType, LocalDate transactionDate, Double amount, Double balance, LocalDateTime createdAt, Long creatorId) {
        this.accountId = accountId;
        this.transactionType = transactionType;
        this.transactionId = transactionId;
        this.transactionSubType = transactionSubType;
        this.transactionDate = transactionDate;
        this.amount = amount;
        this.balance = balance;
        this.createdAt = createdAt;
        this.creatorId = creatorId;
    }

    private AccountTransactionEntity(Long id, Long accountId, TransactionIdentity transactionIdentity, Double amount, Double balance, LocalDateTime createdAt, Long creatorId) {
        this.id = id;
        this.accountId = accountId;
        this.transactionId = transactionIdentity.getTransactionId();
        this.transactionType = transactionIdentity.getType();
        this.transactionSubType = transactionIdentity.getSubtype();
        this.transactionDate = transactionIdentity.getTransactionDate();
        this.amount = amount;
        this.balance = balance;
        this.createdAt = createdAt;
        this.creatorId = creatorId;
    }

    public static AccountTransactionEntity createWithId(Long id, Long accountId, TransactionIdentity transactionIdentity, Double amount, Double balance, LocalDateTime createdAt, Long creatorId) {
        Objects.requireNonNull(id, "id 가 null 이어서는 안됩니다.");
        Objects.requireNonNull(accountId, "accountId 가 null 이어서는 안됩니다.");
        validateTransactionIdentity(transactionIdentity);
        Objects.requireNonNull(amount, "amount 가 null 이어서는 안됩니다.");
        Objects.requireNonNull(balance, "balance 가 null 이어서는 안됩니다.");
        Objects.requireNonNull(createdAt, "createdAt 이 null 이어서는 안됩니다.");
        Objects.requireNonNull(creatorId, "creatorId 가 null 이어서는 안됩니다.");

        return new AccountTransactionEntity(id, accountId, transactionIdentity, amount, balance, createdAt, creatorId);
    }

    private static void validateTransactionIdentity(TransactionIdentity transactionIdentity) {
        Objects.requireNonNull(transactionIdentity.getTransactionId(), "transactionId 가 null 이어서는 안됩니다.");
        Objects.requireNonNull(transactionIdentity.getType(), "transactionType 이 null 이어서는 안됩니다.");
        Objects.requireNonNull(transactionIdentity.getSubtype(), "transactionSubType 이 null 이어서는 안됩니다.");
        Objects.requireNonNull(transactionIdentity.getTransactionDate(), "transactionDate 가 null 이어서는 안됩니다.");
    }

    @DomainEvents
    Collection<Object> domainEvents() {
        accountTransactionDomainEvents.add(new AccountTransactionSaved(this.accountId, this.balance));
        log.debug("The AccountTransactionSaved event has been published." +
                "[transactionType: {}, transactionSubType: {}, transactionId: {}]", this.transactionType, this.transactionSubType, this.transactionId);
        return accountTransactionDomainEvents;
    }

    @AfterDomainEventPublication
    void callbackMethod() {
        accountTransactionDomainEvents.clear();
    }
}
