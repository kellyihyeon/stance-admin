package com.github.kellyihyeon.stanceadmin.application.account;

import com.github.kellyihyeon.stanceadmin.application.account.dto.AccountCreation;
import com.github.kellyihyeon.stanceadmin.domain.account.Account;
import com.github.kellyihyeon.stanceadmin.domain.account.AccountRepository;
import com.github.kellyihyeon.stanceadmin.models.AccountBalance;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    public void createAccount(AccountCreation accountCreation) {
        Long loggedInAdminId = 999L;
        Double initialBalance = (double) 0;

        Account account = Account.createWithoutId(
                accountCreation.isDefault(),
                accountCreation.accountNickname(),
                initialBalance,
                accountCreation.accountHolder(),
                accountCreation.accountNumber(),
                accountCreation.bank(),
                accountCreation.usageStartDate(),
                accountCreation.status(),
                LocalDateTime.now(),
                loggedInAdminId
        );

        accountRepository.saveAccount(account);
    }

    public AccountBalance getBalance() {
        Account defaultAccount = accountRepository.getDefaultAccount();
        return new AccountBalance().balance(defaultAccount.getBalance());
    }

    public Account getDefaultAccount() {
        return accountRepository.getDefaultAccount();
    }

    public void updateBalance(Double balance) {

    }
}
