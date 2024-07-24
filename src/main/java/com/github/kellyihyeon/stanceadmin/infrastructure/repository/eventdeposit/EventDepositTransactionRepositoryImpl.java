package com.github.kellyihyeon.stanceadmin.infrastructure.repository.eventdeposit;

import com.github.kellyihyeon.stanceadmin.application.eventdeposit.EventDepositTransactionMapper;
import com.github.kellyihyeon.stanceadmin.domain.eventdeposit.EventDepositTransaction;
import com.github.kellyihyeon.stanceadmin.domain.eventdeposit.EventDepositTransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class EventDepositTransactionRepositoryImpl implements EventDepositTransactionRepository {

    private final JpaEventDepositTransactionEntityRepository jpaRepository;

    @Override
    public Long saveEventDepositTransaction(EventDepositTransaction domain) {
        EventDepositTransactionEntity entity = EventDepositTransactionMapper.toEntity(domain);
        jpaRepository.save(entity);
        return 1L;
    }
}
