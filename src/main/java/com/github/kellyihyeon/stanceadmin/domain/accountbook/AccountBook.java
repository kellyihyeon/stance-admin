package com.github.kellyihyeon.stanceadmin.domain.accountbook;

import com.github.kellyihyeon.stanceadmin.application.accountbook.dto.TransactionRecord;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "account_books")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AccountBook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "transaction_id")
    private Long transactionId;

    @Enumerated(EnumType.STRING)
    @Column(name = "transaction_type")
    private TransactionType transactionType;

    @Column(name = "transaction_date")
    private LocalDate transactionDate;

    @Column(name = "transaction_party")
    private String transactionParty;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "balance")
    private BigDecimal balance;

    private Long creatorId;

    private LocalDateTime createdDate;

    private Long updaterId;

    private LocalDateTime updatedDate;


    // TODO. 로그인 기능 추가 시 수정
    private AccountBook(
            Long transactionId,
            TransactionType transactionType,
            LocalDate transactionDate,
            String transactionParty,
            BigDecimal amount,
            BigDecimal balance
    ) {
        this.transactionId = transactionId;
        this.transactionType = transactionType;
        this.transactionDate = transactionDate;
        this.transactionParty = transactionParty;
        this.amount = amount;
        this.balance = balance;
        this.creatorId = 1L;
        this.createdDate = LocalDateTime.now();
    }


    public BigDecimal deposit(BigDecimal amount) {
        return this.balance.add(amount);
    }

    private BigDecimal withdraw(BigDecimal amount) {
        return this.balance.subtract(amount);
    }

    public AccountBook updateBalance(
            Long transactionId,
            TransactionType transactionType,
            LocalDate transactionDate,
            String summary,
            BigDecimal amount
    ) {
        return new AccountBook(
                transactionId,
                transactionType,
                transactionDate,
                summary,
                amount,
                deposit(amount)
        );
    }

    public AccountBook updateBalance(TransactionRecord transactionRecord) {
        return new AccountBook(
                transactionRecord.transactionId(),
                transactionRecord.transactionType(),
                transactionRecord.transactionDate(),
                transactionRecord.transactionParty(),
                transactionRecord.amount(),
                withdraw(transactionRecord.amount())
        );
    }

    public boolean isDeposit() {
        return TransactionType.DEPOSIT.equals(this.transactionType);
    }

    public boolean isWithdraw() {
        return TransactionType.WITHDRAW.equals(this.transactionType);
    }
}
