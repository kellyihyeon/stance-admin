package com.github.kellyihyeon.stanceadmin.domain.account;

import java.util.List;

public interface AccountRepository {

    void createAccount(Account account);

    List<Account> getAllAccounts();

    void modifyAccount(Account updatedAccount);

    void deleteAccount(Long id);

    Account getDefaultAccount();
}
