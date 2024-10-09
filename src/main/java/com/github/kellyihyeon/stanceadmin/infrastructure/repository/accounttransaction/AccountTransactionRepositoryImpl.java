package com.github.kellyihyeon.stanceadmin.infrastructure.repository.accounttransaction;

import com.github.kellyihyeon.stanceadmin.application.accounttransaction.AccountTransactionMapperImpl;
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
    private final AccountTransactionMapperImpl mapper;

    @Override
    public void saveAccountTransaction(AccountTransaction accountTransaction) {
        AccountTransactionEntity entity = mapper.toEntity(accountTransaction);
        jpaRepository.save(entity);
    }

    @Override
    public AccountTransaction findLatestAccountTransaction() {
        AccountTransactionEntity entity = jpaRepository.findFirstByOrderByCreatedAtDesc();
        return mapper.toDomain(entity);
    }

    @Override
    public List<AccountTransaction> findAccountTransactionFrom(LocalDate fromTransactionDate) {
        List<AccountTransactionEntity> transactions = jpaRepository.findByTransactionDateGreaterThanEqualOrderByTransactionDateAscCreatedAtAsc(fromTransactionDate);
        return mapper.toDomains(transactions);
    }

    @Override
    public void saveAll(List<AccountTransaction> transactions) {
        jpaRepository.saveAll(mapper.toEntities(transactions));
    }
}
