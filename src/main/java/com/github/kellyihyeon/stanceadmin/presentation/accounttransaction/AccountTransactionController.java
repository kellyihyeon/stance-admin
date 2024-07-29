package com.github.kellyihyeon.stanceadmin.presentation.accounttransaction;

import com.github.kellyihyeon.stanceadmin.apis.AccountTransactionApi;
import com.github.kellyihyeon.stanceadmin.application.accounttransaction.AccountTransactionService;
import com.github.kellyihyeon.stanceadmin.models.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AccountTransactionController implements AccountTransactionApi {

    private final AccountTransactionService service;


    @Override
    public ResponseEntity<List<AccountTransactions>> getAllAccountTransactions() {
        return null;
    }

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

    @Override
    public ResponseEntity<Void> saveCardPaymentTransaction(CardPayment cardPayment) {
        return null;
    }

    @Override
    public ResponseEntity<Void> saveTransferTransaction(Transfer transfer) {
        return null;
    }
}
