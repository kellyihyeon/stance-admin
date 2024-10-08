package com.github.kellyihyeon.stanceadmin.application.accounttransaction;

import com.github.kellyihyeon.stanceadmin.application.account.AccountService;
import com.github.kellyihyeon.stanceadmin.domain.accounttransaction.AccountTransaction;
import com.github.kellyihyeon.stanceadmin.domain.accounttransaction.AccountTransactionRepository;
import com.github.kellyihyeon.stanceadmin.domain.accounttransaction.TransactionIdentity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
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
        AccountTransaction accountTransaction = AccountTransaction.createWithoutId(
                defaultAccountId,
                transactionIdentity,
                amount,
                now,
                loggedInId);
        accountTransaction.calculateBalance(getLatestBalance());
        repository.saveAccountTransaction(accountTransaction);
        log.debug("AccountTransaction saved successfully. The event will be triggered. [new balance is {}]", accountTransaction.getBalance());
    }

    private BigDecimal getLatestBalance() {
        AccountTransaction latestAccountTransaction = repository.findLatestAccountTransaction();

        if (Objects.isNull(latestAccountTransaction)) {
            return BigDecimal.ZERO;
        }

        return BigDecimal.valueOf(latestAccountTransaction.getBalance());
    }

    public void recalculateBalanceFrom(LocalDate fromTransactionDate) {
        List<AccountTransaction> transactions = repository.findAccountTransactionFrom(fromTransactionDate);
    }
}
