package com.github.kellyihyeon.stanceadmin.domain.account;

import com.github.kellyihyeon.stanceadmin.infrastructure.repository.account.AccountEntity;

import java.util.List;

public interface AccountRepository {

    void createAccount(AccountEntity accountEntity);

    List<Account> getAllAccounts();

    void modifyAccount(Account updatedAccount);

    void deleteAccount(Long id);
}
