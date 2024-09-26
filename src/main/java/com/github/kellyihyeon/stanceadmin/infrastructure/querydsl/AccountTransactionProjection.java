package com.github.kellyihyeon.stanceadmin.infrastructure.querydsl;

import com.github.kellyihyeon.stanceadmin.domain.accounttransaction.TransactionType;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@ToString
public class AccountTransactionProjection {

    private Long id;

    private LocalDate transactionDate;

    private Long transactionId;

    private TransactionType transactionType;

    private String detailType;

    private String transactionParty;

    private Double amount;

    private Double balance;

    private LocalDateTime createdAt;

}
