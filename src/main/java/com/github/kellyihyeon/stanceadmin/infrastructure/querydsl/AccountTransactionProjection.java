package com.github.kellyihyeon.stanceadmin.infrastructure.querydsl;

import com.github.kellyihyeon.stanceadmin.domain.accounttransaction.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface AccountTransactionProjection {

    Long getId();

    LocalDate getTransactionDate();

    Long getTransactionId();

    TransactionType getTransactionType();

    String getCategory();

    String getTransactionParty();

    BigDecimal getAmount();

    BigDecimal getBalance();

}
