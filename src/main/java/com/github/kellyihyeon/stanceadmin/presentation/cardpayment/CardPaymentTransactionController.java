package com.github.kellyihyeon.stanceadmin.presentation.cardpayment;

import com.github.kellyihyeon.stanceadmin.apis.CardPaymentTransactionApi;
import com.github.kellyihyeon.stanceadmin.application.cardpayment.CardPaymentTransactionService;
import com.github.kellyihyeon.stanceadmin.application.cardpayment.dto.CardPaymentCreation;
import com.github.kellyihyeon.stanceadmin.domain.transfertransaction.ExpenseCategory;
import com.github.kellyihyeon.stanceadmin.models.CardPaymentInput;
import com.github.kellyihyeon.stanceadmin.presentation.TimeConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequiredArgsConstructor
public class CardPaymentTransactionController implements CardPaymentTransactionApi {

    private final CardPaymentTransactionService service;

    @Override
    public ResponseEntity<Void> saveCardPaymentTransaction(CardPaymentInput cardPaymentInput) {
        CardPaymentCreation serviceDto = new CardPaymentCreation(
                cardPaymentInput.getCardHolderId(),
                ExpenseCategory.valueOf(cardPaymentInput.getExpenseCategory().getValue()),
                cardPaymentInput.getCardUsageLocation(),
                cardPaymentInput.getAmount(),
                TimeConverter.convertToLocalDate(cardPaymentInput.getExpenseDate()),
                cardPaymentInput.getDescription()
        );

        service.saveCardPaymentTransaction(serviceDto);
        return ResponseEntity.created(URI.create("")).build();
    }
}
