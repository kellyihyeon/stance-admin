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
    private final EventDepositTransactionMapper mapper;

    @Override
    public Long saveEventDepositTransaction(EventDepositTransaction domain) {
        EventDepositTransactionEntity entity = mapper.toEntity(domain);
        return jpaRepository.save(entity).getId();
    }
}
