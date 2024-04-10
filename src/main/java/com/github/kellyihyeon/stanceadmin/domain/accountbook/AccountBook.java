package com.github.kellyihyeon.stanceadmin.domain.accountbook;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    @Column(name = "summary")
    private String summary;

    @Column(name = "amount")
    private Long amount;

    @Column(name = "balance")
    private Long balance;

    private Long creatorId;

    private LocalDateTime createdDate;

    private Long updaterId;

    private LocalDateTime updatedDate;


    // TODO. 로그인 기능 추가 시 수정
    private AccountBook(
            Long transactionId,
            TransactionType transactionType,
            LocalDate transactionDate,
            String summary,
            Long amount,
            Long balance
    ) {
        this.transactionId = transactionId;
        this.transactionType = transactionType;
        this.transactionDate = transactionDate;
        this.summary = summary;
        this.amount = amount;
        this.balance = balance;
        this.creatorId = 1L;
        this.createdDate = LocalDateTime.now();
    }


    public Long deposit(Long amount) {
        return this.balance + amount;
    }

    public AccountBook updateBalance(
            Long transactionId,
            TransactionType transactionType,
            LocalDate transactionDate,
            String summary,
            Long amount
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
}
