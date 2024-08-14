package com.github.kellyihyeon.stanceadmin.presentation.transfertransaction;

import com.github.kellyihyeon.stanceadmin.apis.TrnsferTransactionApi;
import com.github.kellyihyeon.stanceadmin.models.TransferInput;
import org.springframework.http.ResponseEntity;

public class TransferTransactionController implements TrnsferTransactionApi {

    @Override
    public ResponseEntity<Void> saveTransferTransaction(TransferInput transferInput) {
        return null;
    }
}
