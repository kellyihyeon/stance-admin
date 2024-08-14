package com.github.kellyihyeon.stanceadmin.infrastructure.entity.accounttransaction;

import com.github.kellyihyeon.stanceadmin.domain.accounttransaction.AccountTransactionSaved;
import com.github.kellyihyeon.stanceadmin.domain.accounttransaction.TransactionSubType;
import com.github.kellyihyeon.stanceadmin.domain.accounttransaction.TransactionType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.AfterDomainEventPublication;
import org.springframework.data.domain.DomainEvents;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Slf4j
@Entity
@Getter
@Table(name = "account_transactions")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AccountTransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "account_id", nullable = false)
    private Long accountId;

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

    public AccountTransactionEntity(Long accountId, TransactionType transactionType, Long transactionId, TransactionSubType transactionSubType, Double amount, Double balance, LocalDateTime createdAt, Long creatorId) {
        this.accountId = accountId;
        this.transactionType = transactionType;
        this.transactionId = transactionId;
        this.transactionSubType = transactionSubType;
        this.amount = amount;
        this.balance = balance;
        this.createdAt = createdAt;
        this.creatorId = creatorId;
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
