package com.github.kellyihyeon.stanceadmin.presentation.accounttransaction;

import com.github.kellyihyeon.stanceadmin.apis.AccountTransactionApi;
import com.github.kellyihyeon.stanceadmin.application.accounttransaction.AccountTransactionService;
import com.github.kellyihyeon.stanceadmin.models.MonthlySummary;
import com.github.kellyihyeon.stanceadmin.models.PagedAccountTransactionResponse;
import com.github.kellyihyeon.stanceadmin.models.TopDeposits;
import com.github.kellyihyeon.stanceadmin.models.TopExpenses;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AccountTransactionController implements AccountTransactionApi {

    private final AccountTransactionService service;

    @Override
    public ResponseEntity<PagedAccountTransactionResponse> getAllAccountTransactions(Integer page, Integer size) {
        PageRequest pageable = PageRequest.of(page, size);

        return ResponseEntity.ok(null);
    };

    @Override
    public ResponseEntity<List<MonthlySummary>> getMonthlySummaryByYear(Integer year) {
        return null;
    }

    @Override
    public ResponseEntity<List<TopDeposits>> getTop3Deposits(Integer year, Integer month) {
        return null;
    }

    @Override
    public ResponseEntity<List<TopExpenses>> getTop3Expenses(Integer year, Integer month) {
        return null;
    }
}
