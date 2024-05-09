package com.github.kellyihyeon.stanceadmin.presentation.accountbook;

import com.github.kellyihyeon.stanceadmin.application.accountbook.AccountBookService;
import com.github.kellyihyeon.stanceadmin.application.accountbook.dto.*;
import com.github.kellyihyeon.stanceadmin.domain.SearchingPeriodType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account-book")
@RequiredArgsConstructor
public class AccountBookController {

    private final AccountBookService accountBookService;


    @GetMapping
    public ResponseEntity<List<AccountBookResponse>> retrieveAccountBooksByPeriod(
            @RequestParam(defaultValue = "ONE_MONTH") SearchingPeriodType period
    ) {
        List<AccountBookResponse> result = accountBookService.retrieveAccountBooksByPeriod(period);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{year}")
    public ResponseEntity<List<FinancialTransactionResponse>> retrieveAccountBooksByYear(@PathVariable int year) {
        List<FinancialTransactionResponse> result = accountBookService.retrieveAccountBooksByYear(year);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/balance")
    public ResponseEntity<BalanceResponse> retrieveLatestBalance() {
        BalanceResponse balance = accountBookService.retrieveLatestBalance();
        return ResponseEntity.ok(balance);
    }
}
