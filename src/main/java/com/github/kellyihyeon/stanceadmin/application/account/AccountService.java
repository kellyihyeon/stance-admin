package com.github.kellyihyeon.stanceadmin.application.account;

import com.github.kellyihyeon.stanceadmin.application.account.dto.AccountCreation;
import com.github.kellyihyeon.stanceadmin.domain.account.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    public void createAccount(AccountCreation accountCreation) {
        System.out.println("create account!");
    }
}
