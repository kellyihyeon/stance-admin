package com.github.kellyihyeon.stanceadmin.application.bankdeposit;

import com.github.kellyihyeon.stanceadmin.application.accounttransaction.AccountTransactionService;
import com.github.kellyihyeon.stanceadmin.application.bankdeposit.dto.BankDepositCreation;
import com.github.kellyihyeon.stanceadmin.domain.accounttransaction.TransactionIdentity;
import com.github.kellyihyeon.stanceadmin.domain.accounttransaction.TransactionSubType;
import com.github.kellyihyeon.stanceadmin.domain.accounttransaction.TransactionType;
import com.github.kellyihyeon.stanceadmin.domain.bankdeposit.BankDepositTransaction;
import com.github.kellyihyeon.stanceadmin.domain.bankdeposit.BankDepositTransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class BankDepositTransactionService {

    private final BankDepositTransactionRepository repository;
    private final BankDepositTransactionMapper mapper;

    private final AccountTransactionService accountTransactionService;


    @Transactional
    public void saveBankDepositTransaction(BankDepositCreation serviceDto) {
        Long loggedInId = 999L;
        LocalDateTime now = LocalDateTime.now();

        BankDepositTransaction bankDepositTransaction = mapper.toDomain(serviceDto, loggedInId, now);
        BankDepositTransaction persistedBankDepositTransaction = repository.saveBankDepositTransaction(bankDepositTransaction);

        TransactionIdentity transactionIdentity = TransactionIdentity.create(
                persistedBankDepositTransaction.getId(),
                TransactionType.DEPOSIT,
                TransactionSubType.BANK,
                persistedBankDepositTransaction.getDepositDate()
        );

        accountTransactionService.saveAccountTransaction(transactionIdentity, persistedBankDepositTransaction.getAmount());
    }
}
