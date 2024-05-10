package com.github.kellyihyeon.stanceadmin.application.withdraw;

import com.github.kellyihyeon.stanceadmin.application.withdraw.dto.ExpenseCategoryTotalSum;
import com.github.kellyihyeon.stanceadmin.application.withdraw.dto.TopExpenseCategoriesResponse;
import com.github.kellyihyeon.stanceadmin.infrastructure.repository.withdraw.WithdrawRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WithdrawService {

    private final WithdrawRepository withdrawRepository;


    public TopExpenseCategoriesResponse getTopWithdrawsByCategoryTotalSum(Year year, Month month) {
        final int FIRST_DAY = 1;
        LocalDate startDate = LocalDate.of(year.getValue(), month.getValue(), FIRST_DAY);
        LocalDate endDate = startDate.withDayOfMonth(startDate.lengthOfMonth());

        final int TOP_CATEGORY_LIMIT_COUNT = 3;
        List<ExpenseCategoryTotalSum> expenseCategoryTotalSums = withdrawRepository.findTopCategoryTotalSumByExpenseDateBetween(
                startDate,
                endDate,
                TOP_CATEGORY_LIMIT_COUNT
        );


        List<ExpenseCategoryTotalSum> history = calculateRankings(expenseCategoryTotalSums);

        return TopExpenseCategoriesResponse
                .builder()
                .year(year)
                .month(month.getValue())
                .total(calculateTotalSumAllCategories(history))
                .history(history)
                .build();
    }

    private BigDecimal calculateTotalSumAllCategories(List<ExpenseCategoryTotalSum> history) {
        BigDecimal total = BigDecimal.ZERO;

        for (ExpenseCategoryTotalSum expenseCategoryTotalSum : history) {
            total = total.add(expenseCategoryTotalSum.getTotal());
        }

        return total;
    }

    private List<ExpenseCategoryTotalSum> calculateRankings(List<ExpenseCategoryTotalSum> expenseCategoryTotalSums) {
        List<ExpenseCategoryTotalSum> history = new ArrayList<>();

        int rank = 1;
        for (ExpenseCategoryTotalSum expenseCategoryTotalSum : expenseCategoryTotalSums) {
            history.add(ExpenseCategoryTotalSum.builder()
                    .rank(rank++)
                    .expenseCategory(expenseCategoryTotalSum.getExpenseCategory())
                    .total(expenseCategoryTotalSum.getTotal())
                    .build());
        }

        return history;
    }

}
