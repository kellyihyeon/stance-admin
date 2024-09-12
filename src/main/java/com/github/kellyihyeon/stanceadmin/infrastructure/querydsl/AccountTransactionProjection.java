package com.github.kellyihyeon.stanceadmin.infrastructure.querydsl;

import com.github.kellyihyeon.stanceadmin.domain.accounttransaction.TransactionType;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@ToString
public class AccountTransactionProjection {

    private Long id;

    private LocalDate transactionDate;

    private Long transactionId;

    private TransactionType transactionType;

    private String category;

    private String transactionParty;

    private Double amount;

    private Double balance;

}
