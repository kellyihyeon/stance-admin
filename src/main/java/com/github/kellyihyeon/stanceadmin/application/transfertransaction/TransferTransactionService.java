package com.github.kellyihyeon.stanceadmin.application.transfertransaction;

import com.github.kellyihyeon.stanceadmin.application.accounttransaction.AccountTransactionService;
import com.github.kellyihyeon.stanceadmin.application.transfertransaction.dto.TransferCreation;
import com.github.kellyihyeon.stanceadmin.domain.accounttransaction.TransactionIdentity;
import com.github.kellyihyeon.stanceadmin.domain.accounttransaction.TransactionSubType;
import com.github.kellyihyeon.stanceadmin.domain.accounttransaction.TransactionType;
import com.github.kellyihyeon.stanceadmin.domain.transfertransaction.TransferTransaction;
import com.github.kellyihyeon.stanceadmin.domain.transfertransaction.TransferTransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TransferTransactionService {

    private final TransferTransactionRepository repository;
    private final TransferTransactionMapper mapper;

    private final AccountTransactionService accountTransactionService;

    @Transactional
    public void saveTransferTransaction(TransferCreation serviceDto) {
        LocalDateTime now = LocalDateTime.now();
        Long loggedInId = 999L;

        TransferTransaction transferTransaction = repository.save(mapper.toDomain(serviceDto, loggedInId, now));

        TransactionIdentity transactionIdentity = TransactionIdentity.create(
                transferTransaction.getId(),
                TransactionType.WITHDRAW,
                TransactionSubType.TRANSFER
        );
        accountTransactionService.saveAccountTransaction(transactionIdentity, transferTransaction.getAmount().doubleValue());
    }
}
