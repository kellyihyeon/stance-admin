package com.github.kellyihyeon.stanceadmin.infrastructure.repository.accounttransaction;

import com.github.kellyihyeon.stanceadmin.application.accounttransaction.AccountTransactionMapper;
import com.github.kellyihyeon.stanceadmin.domain.accounttransaction.AccountTransaction;
import com.github.kellyihyeon.stanceadmin.domain.accounttransaction.AccountTransactionRepository;
import com.github.kellyihyeon.stanceadmin.infrastructure.entity.accounttransaction.AccountTransactionEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class AccountTransactionRepositoryImpl implements AccountTransactionRepository {

    private final JpaAccountTransactionEntityRepository jpaRepository;

    @Override
    public void saveAccountTransaction(AccountTransaction accountTransaction) {
        AccountTransactionEntity entity = AccountTransactionMapper.toEntity(accountTransaction);
        jpaRepository.save(entity);
    }

    @Override
    public AccountTransaction findLatestAccountTransaction() {
        AccountTransactionEntity entity = jpaRepository.findFirstByOrderByCreatedAtDesc();
        return AccountTransactionMapper.toDomain(entity);
    }

    @Override
    public void updateBalanceFrom(LocalDate fromTransactionDate) {
        List<AccountTransactionEntity> transactions = jpaRepository.findByTransactionDateGreaterThanEqualOrderByTransactionDateAsc(fromTransactionDate);
    }
}
