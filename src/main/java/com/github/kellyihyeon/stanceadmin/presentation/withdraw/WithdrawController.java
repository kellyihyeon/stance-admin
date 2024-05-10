package com.github.kellyihyeon.stanceadmin.presentation.withdraw;

import com.github.kellyihyeon.stanceadmin.application.accountbook.AccountBookService;
import com.github.kellyihyeon.stanceadmin.application.withdraw.WithdrawService;
import com.github.kellyihyeon.stanceadmin.application.withdraw.dto.CardPaymentRequest;
import com.github.kellyihyeon.stanceadmin.application.withdraw.dto.TopExpenseCategoriesResponse;
import com.github.kellyihyeon.stanceadmin.application.withdraw.dto.TransferRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.Month;
import java.time.Year;

@RestController
@RequiredArgsConstructor
@RequestMapping("/withdraws")
public class WithdrawController {

    private final AccountBookService accountBookService;
    private final WithdrawService withdrawService;

    @PostMapping("/transfer")
    void withdrawByTransfer(
            @RequestBody TransferRequest transferRequest
    ) {
        accountBookService.processTransferWithdrawal(transferRequest);
    }

    @PostMapping("/card-payment")
    void withdrawByCardPayment(
            @RequestBody @Validated CardPaymentRequest cardPaymentRequest
    ) {
        accountBookService.processCardPaymentWithdrawal(cardPaymentRequest);
    }

    @GetMapping("/categories/{year}/{month}")
    public ResponseEntity<TopExpenseCategoriesResponse> getTopWithdrawsByCategoryTotalSum(
            @PathVariable Year year,
            @PathVariable Month month
    ) {
        TopExpenseCategoriesResponse topCategories = withdrawService.getTopWithdrawsByCategoryTotalSum(year, month);
        return ResponseEntity.ok(topCategories);
    }
}
