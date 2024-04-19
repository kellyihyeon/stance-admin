package com.github.kellyihyeon.stanceadmin.domain.accountbook;

import com.github.kellyihyeon.stanceadmin.application.accountbook.dto.AccountBookResponse;

import java.time.LocalDate;
import java.util.List;

public interface AccountBookRepositoryCustom {

    List<AccountBookResponse> findAccountBookByTransactionDateBetweenOrderByTransactionDateDesc
            (LocalDate startDate, LocalDate endDate);

}
