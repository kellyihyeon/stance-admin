package com.github.kellyihyeon.stanceadmin.application.accounttransaction;

import com.github.kellyihyeon.stanceadmin.application.account.AccountService;
import com.github.kellyihyeon.stanceadmin.domain.account.AccountRepository;
import com.github.kellyihyeon.stanceadmin.domain.accounttransaction.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AccountTransactionService {

    private final AccountTransactionRepository repository;
    private final AccountRepository accountRepository;

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

        accountService.updateBalance(balance);
    }

    private Double getLatestBalance() {
        AccountTransaction latestAccountTransaction = repository.findLatestAccountTransaction();

        if (Objects.isNull(latestAccountTransaction)) {
            return (double) 0;
        }

        return latestAccountTransaction.getBalance();
    }

    public void saveAccountTransaction(Long transactionId, TransactionType type, TransactionSubType subType) {
        LocalDateTime now = LocalDateTime.now();
        Long loggedInId = 999L;

        // TODO: 기본으로 설정된 계좌가 없을 경우 예외처리
        Long defaultAccountId = accountRepository.getDefaultAccount().getId();

        AccountTransaction accountTransaction = AccountTransaction.create(
                null,
                defaultAccountId,
                type,
                transactionId,
                subType,
                now,
                loggedInId
        );

        repository.saveAccountTransaction(accountTransaction);
    }
}
