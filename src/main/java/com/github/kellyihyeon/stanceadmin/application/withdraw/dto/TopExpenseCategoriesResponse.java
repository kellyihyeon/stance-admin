package com.github.kellyihyeon.stanceadmin.application.withdraw.dto;

import lombok.Builder;

import java.math.BigDecimal;
import java.time.Year;
import java.util.List;

public record TopExpenseCategoriesResponse(
        Year year,
        Integer month,
        BigDecimal total,
        List<ExpenseCategoryTotalSum> history
) {

    @Builder
    public TopExpenseCategoriesResponse {
    }
}
