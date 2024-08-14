package com.github.kellyihyeon.stanceadmin.application.accounttransaction;

import com.github.kellyihyeon.stanceadmin.application.account.AccountService;
import com.github.kellyihyeon.stanceadmin.domain.accounttransaction.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountTransactionService {

    private final AccountTransactionRepository repository;

    private final AccountService accountService;


    @Transactional
    public void saveAccountTransaction(TransactionIdentity transactionIdentity, Double amount) {
        // 유저 객체
        LocalDateTime now = LocalDateTime.now();
        Long loggedInId = 999L;

        Long defaultAccountId = accountService.getDefaultAccount().getId();
        AccountTransaction accountTransaction = AccountTransaction.create(
                defaultAccountId,
                transactionIdentity.getType(),
                transactionIdentity.getTransactionId(),
                transactionIdentity.getSubtype(),
                amount,
                now,
                loggedInId
        );

        Double balance = accountTransaction.addAmountToBalance(getLatestBalance());
        repository.saveAccountTransaction(accountTransaction);
        log.debug("AccountTransaction saved successfully. The event will be triggered. [new balance is {}]", balance);
    }

    private Double getLatestBalance() {
        AccountTransaction latestAccountTransaction = repository.findLatestAccountTransaction();

        if (Objects.isNull(latestAccountTransaction)) {
            return (double) 0;
        }

        return latestAccountTransaction.getBalance();
    }
}
