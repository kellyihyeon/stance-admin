package com.github.kellyihyeon.stanceadmin.domain.accounttransaction;

import com.github.kellyihyeon.stanceadmin.application.account.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Slf4j
@Component
@RequiredArgsConstructor
public class AccountTransactionEventHandler {

    private final AccountService accountService;

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void handleAccountTransactionEvent(AccountTransactionSaved event) {
        log.debug("The handler will run. [account ID is {}, latest balance is {}]", event.getAccountId(), event.getBalance());

        accountService.updateBalance(event.getAccountId(), event.getBalance());
    }
}
