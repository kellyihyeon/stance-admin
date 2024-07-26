package com.github.kellyihyeon.stanceadmin.infrastructure.repository.bankdeposit;

import com.github.kellyihyeon.stanceadmin.application.bankdeposit.BankDepositTransactionMapper;
import com.github.kellyihyeon.stanceadmin.domain.bankdeposit.BankDepositTransaction;
import com.github.kellyihyeon.stanceadmin.domain.bankdeposit.BankDepositTransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class BankDepositTransactionRepositoryImpl implements BankDepositTransactionRepository {

    private final JpaBankDepositTransactionEntityRepository jpaRepository;
    private final BankDepositTransactionMapper mapper;

    @Override
    public BankDepositTransaction saveBankDepositTransaction(BankDepositTransaction bankDepositTransaction) {
        BankDepositTransactionEntity entity = jpaRepository.save(mapper.toEntity(bankDepositTransaction));
        return mapper.toDomain(entity);
    }
}
