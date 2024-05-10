package com.github.kellyihyeon.stanceadmin.application.withdraw.dto;

import com.github.kellyihyeon.stanceadmin.domain.withdraw.ExpenseCategory;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class ExpenseCategoryTotalSum {

    private Integer rank;

    private ExpenseCategory expenseCategory;

    private BigDecimal total;

    @QueryProjection
    public ExpenseCategoryTotalSum(ExpenseCategory expenseCategory, BigDecimal total) {
        this.expenseCategory = expenseCategory;
        this.total = total;
    }

    @Builder
    public ExpenseCategoryTotalSum(int rank, ExpenseCategory expenseCategory, BigDecimal total) {
        this.rank = rank;
        this.expenseCategory = expenseCategory;
        this.total = total;
    }
}
