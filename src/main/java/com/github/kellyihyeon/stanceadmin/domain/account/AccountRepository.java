package com.github.kellyihyeon.stanceadmin.domain.account;

import java.util.List;

public interface AccountRepository {

    void saveAccount(Account account);

    List<Account> getAllAccounts();

    void modifyAccount(Account updatedAccount);

    void deleteAccount(Long id);

    Account getDefaultAccount();

    Account findById(Long accountId);

    void updateBalance(Account account);

}
