package com.github.kellyihyeon.stanceadmin.infrastructure.repository.transfertransaction;

import com.github.kellyihyeon.stanceadmin.application.transfertransaction.TransferTransactionMapper;
import com.github.kellyihyeon.stanceadmin.domain.transfertransaction.TransferTransaction;
import com.github.kellyihyeon.stanceadmin.domain.transfertransaction.TransferTransactionRepository;
import com.github.kellyihyeon.stanceadmin.infrastructure.entity.trasfertransaction.TransferTransactionEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class TransferTransactionRepositoryImpl implements TransferTransactionRepository {

    private final JpaTransferTransactionEntityRepository jpaRepository;
    private final TransferTransactionMapper mapper;

    @Override
    public TransferTransaction save(TransferTransaction transferTransaction) {
        TransferTransactionEntity entity = jpaRepository.save(mapper.toEntity(transferTransaction));
        return mapper.toDomain(entity);
    }
}
