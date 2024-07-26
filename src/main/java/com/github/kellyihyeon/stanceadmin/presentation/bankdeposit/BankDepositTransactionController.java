package com.github.kellyihyeon.stanceadmin.presentation.bankdeposit;

import com.github.kellyihyeon.stanceadmin.apis.BankDepositTransactionApi;
import com.github.kellyihyeon.stanceadmin.application.bankdeposit.BankDepositTransactionService;
import com.github.kellyihyeon.stanceadmin.application.bankdeposit.dto.BankDepositCreation;
import com.github.kellyihyeon.stanceadmin.domain.bankdeposit.DepositType;
import com.github.kellyihyeon.stanceadmin.models.BankDepositInput;
import com.github.kellyihyeon.stanceadmin.presentation.TimeConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequiredArgsConstructor
public class BankDepositTransactionController implements BankDepositTransactionApi {

    private final BankDepositTransactionService bankDepositService;


    @Override
    public ResponseEntity<Void> saveBankDepositTransaction(BankDepositInput bankDepositInput) {
        BankDepositCreation bankDepositCreation = BankDepositCreation.create(
                DepositType.valueOf(bankDepositInput.getType().getValue()),
                bankDepositInput.getDepositSource(),
                bankDepositInput.getAmount(),
                TimeConverter.convertToLocalDate(bankDepositInput.getDepositDate()),
                bankDepositInput.getDescription()
        );

        bankDepositService.saveBankDepositTransaction(bankDepositCreation);
        return ResponseEntity.created(URI.create("")).build();
    }
}
