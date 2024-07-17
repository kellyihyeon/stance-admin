package com.github.kellyihyeon.stanceadmin.infrastructure.repository.account;

import com.github.kellyihyeon.stanceadmin.application.account.AccountMapper;
import com.github.kellyihyeon.stanceadmin.domain.account.Account;
import com.github.kellyihyeon.stanceadmin.domain.account.AccountRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class AccountRepositoryImpl implements AccountRepository {

    private final JpaAccountEntityRepository entityRepository;

    @Override
    public void createAccount(Account account) {
        AccountEntity entity = AccountMapper.toEntity(account);
        entityRepository.save(entity);
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

    @Override
    public Account getDefaultAccount() {
        AccountEntity defaultAccountEntity = entityRepository.findByIsDefault(true)
                .orElseThrow(() -> new EntityNotFoundException("기본 계좌로 설정된 계좌가 없어요."));

        return AccountMapper.toDomain(defaultAccountEntity);
    }
}
