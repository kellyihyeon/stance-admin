package com.github.kellyihyeon.stanceadmin.application.accountbook.dto;


public record FinancialTransactionResponse(
        Integer month,
        MonthlyTransaction monthlyTransaction
) { }
