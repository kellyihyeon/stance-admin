package com.github.kellyihyeon.stanceadmin.presentation.withdraw;

import com.github.kellyihyeon.stanceadmin.application.withdraw.WithdrawService;
import com.github.kellyihyeon.stanceadmin.application.withdraw.dto.CardPaymentRequest;
import com.github.kellyihyeon.stanceadmin.application.withdraw.dto.TopExpenseCategoriesResponse;
import com.github.kellyihyeon.stanceadmin.application.withdraw.dto.TransferRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.Month;
import java.time.Year;

@RestController
@RequiredArgsConstructor
@RequestMapping("/withdraws")
public class WithdrawController {

    private final WithdrawService withdrawService;

    @PostMapping("/transfer")
    public ResponseEntity<Void> withdrawByTransfer(
            @RequestBody TransferRequest transferRequest
    ) {
        withdrawService.processTransferWithdrawal(transferRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/card-payment")
    public ResponseEntity<Void> withdrawByCardPayment(
            @RequestBody @Validated CardPaymentRequest cardPaymentRequest
    ) {
        withdrawService.processCardPaymentWithdrawal(cardPaymentRequest);
        return new ResponseEntity<>(HttpStatus.OK);
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
