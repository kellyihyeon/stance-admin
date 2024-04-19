package com.github.kellyihyeon.stanceadmin.application.accountbook.dto;

import com.github.kellyihyeon.stanceadmin.domain.accountbook.TransactionType;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@ToString
@NoArgsConstructor
public class AccountBookResponse {
    private Long accountBookId;
    private TransactionType transactionType;
    private BigDecimal balance;
    private LocalDate transactionDate;
    private String transactionParty;
    private BigDecimal amount;
    private String description;


    @QueryProjection
    public AccountBookResponse(Long accountBookId, TransactionType transactionType, BigDecimal balance, LocalDate transactionDate, String transactionParty, BigDecimal amount, String description) {
        this.accountBookId = accountBookId;
        this.transactionType = transactionType;
        this.balance = balance;
        this.transactionDate = transactionDate;
        this.transactionParty = transactionParty;
        this.amount = amount;
        this.description = description;
    }
}
