package com.github.kellyihyeon.stanceadmin.infrastructure.repository.account;

import com.github.kellyihyeon.stanceadmin.domain.account.Account;
import com.github.kellyihyeon.stanceadmin.domain.account.AccountRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AccountRepositoryImpl implements AccountRepository {


    @Override
    public void createAccount(Account account) {

    }

    @Override
    public List<Account> getAllAccounts() {
        return null;
    }

    @Override
    public void modifyAccount(Account updatedAccount) {

    }

    @Override
    public void deleteAccount(Long id) {

    }
}
