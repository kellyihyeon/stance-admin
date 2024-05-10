package com.github.kellyihyeon.stanceadmin.infrastructure.repository.withdraw;

import com.github.kellyihyeon.stanceadmin.application.withdraw.dto.ExpenseCategoryTotalSum;
import com.github.kellyihyeon.stanceadmin.application.withdraw.dto.QExpenseCategoryTotalSum;
import com.github.kellyihyeon.stanceadmin.domain.withdraw.WithdrawRepositoryCustom;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static com.github.kellyihyeon.stanceadmin.domain.withdraw.QWithdraw.withdraw;

@Repository
@RequiredArgsConstructor
public class WithdrawRepositoryImpl implements WithdrawRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<ExpenseCategoryTotalSum> findTopCategoryTotalSumByExpenseDateBetween(LocalDate startDate, LocalDate endDate, int limit) {
        NumberPath<BigDecimal> total = Expressions.numberPath(BigDecimal.class, "total");

        return jpaQueryFactory
                .select(
                        new QExpenseCategoryTotalSum(
                                withdraw.expenseCategory,
                                withdraw.amount.sum().as("total")
                        )
                )
                .from(withdraw)
                .where(
                        withdraw.expenseDate.between(startDate, endDate)
                )
                .groupBy(withdraw.expenseCategory)
                .orderBy(total.desc())
                .limit(limit)
                .fetch();
    }
}
