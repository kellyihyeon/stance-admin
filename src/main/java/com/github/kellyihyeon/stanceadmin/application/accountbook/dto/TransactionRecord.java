package com.github.kellyihyeon.stanceadmin.application.accountbook.dto;

import com.github.kellyihyeon.stanceadmin.domain.accountbook.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDate;

public record TransactionRecord(
        TransactionType transactionType,
        Long transactionId,
        LocalDate transactionDate,
        String transactionParty,
        BigDecimal amount
) { }
