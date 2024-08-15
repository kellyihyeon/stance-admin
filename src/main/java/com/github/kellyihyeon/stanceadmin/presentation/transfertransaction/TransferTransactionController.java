package com.github.kellyihyeon.stanceadmin.presentation.transfertransaction;

import com.github.kellyihyeon.stanceadmin.apis.TrnsferTransactionApi;
import com.github.kellyihyeon.stanceadmin.application.transfertransaction.TransferTransactionService;
import com.github.kellyihyeon.stanceadmin.application.transfertransaction.dto.TransferCreation;
import com.github.kellyihyeon.stanceadmin.domain.account.Bank;
import com.github.kellyihyeon.stanceadmin.domain.transfertransaction.ExpenseCategory;
import com.github.kellyihyeon.stanceadmin.models.TransferInput;
import com.github.kellyihyeon.stanceadmin.presentation.TimeConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequiredArgsConstructor
public class TransferTransactionController implements TrnsferTransactionApi {

    private final TransferTransactionService service;

    @Override
    public ResponseEntity<Void> saveTransferTransaction(TransferInput transferInput) {
        TransferCreation serviceDto = new TransferCreation(
                ExpenseCategory.valueOf(transferInput.getExpenseCategory().getValue()),
                transferInput.getRecipientName(),
                Bank.valueOf(transferInput.getBank().getValue()),
                transferInput.getRecipientAccountNumber(),
                transferInput.getAmount(),
                TimeConverter.convertToLocalDate(transferInput.getExpenseDate()),
                transferInput.getDescription()
        );

        service.saveTransferTransaction(serviceDto);
        return ResponseEntity.created(URI.create("")).build();
    }
}
