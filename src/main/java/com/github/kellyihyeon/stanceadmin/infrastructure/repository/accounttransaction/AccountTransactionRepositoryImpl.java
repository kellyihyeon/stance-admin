package com.github.kellyihyeon.stanceadmin.infrastructure.repository.accounttransaction;

import com.github.kellyihyeon.stanceadmin.application.accounttransaction.AccountTransactionMapper;
import com.github.kellyihyeon.stanceadmin.domain.accounttransaction.AccountTransaction;
import com.github.kellyihyeon.stanceadmin.domain.accounttransaction.AccountTransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

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
}
