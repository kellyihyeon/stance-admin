package com.github.kellyihyeon.stanceadmin.infrastructure.repository.account;

import com.github.kellyihyeon.stanceadmin.domain.account.Account;
import com.github.kellyihyeon.stanceadmin.domain.account.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class AccountRepositoryImpl implements AccountRepository {

    private final JpaAccountEntityRepository entityRepository;

    @Override
    public void createAccount(AccountEntity accountEntity) {
        entityRepository.save(accountEntity);
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
