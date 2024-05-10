package com.github.kellyihyeon.stanceadmin.domain.withdraw;


import com.github.kellyihyeon.stanceadmin.application.withdraw.dto.ExpenseCategoryTotalSum;

import java.time.LocalDate;
import java.util.List;

public interface WithdrawRepositoryCustom {

    List<ExpenseCategoryTotalSum> findTopCategoryTotalSumByExpenseDateBetween(LocalDate startDate, LocalDate endDate, int limit);
}
