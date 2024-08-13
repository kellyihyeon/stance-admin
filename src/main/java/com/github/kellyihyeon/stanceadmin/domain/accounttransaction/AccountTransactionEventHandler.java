package com.github.kellyihyeon.stanceadmin.domain.accounttransaction;

import com.github.kellyihyeon.stanceadmin.domain.account.Account;
import com.github.kellyihyeon.stanceadmin.domain.account.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import java.util.Objects;

@Slf4j
@Component
@RequiredArgsConstructor
public class AccountTransactionEventHandler {

    private final AccountRepository accountRepository;

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void handleAccountTransactionEvent(AccountTransactionSaved event) {
        log.debug("The handler will run. [account ID is {}, latest balance is {}]", event.getAccountId(), event.getBalance());
        Account account = accountRepository.findById(event.getAccountId());
        if (Objects.isNull(account)) {
            throw new IllegalArgumentException("존재하지 않는 계좌 ID 입니다.");
        }

        if (!account.isActive()) {
            throw new IllegalStateException("비활성화 된 계좌의 잔액은 수정할 수 없습니다.");
        }

        account.updateBalance(event.getAccountId(), event.getBalance());
        accountRepository.updateBalance(account);
    }
}
