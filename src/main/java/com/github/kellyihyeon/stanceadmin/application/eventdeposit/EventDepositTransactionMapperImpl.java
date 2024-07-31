package com.github.kellyihyeon.stanceadmin.application.eventdeposit;

import com.github.kellyihyeon.stanceadmin.application.eventdeposit.dto.EventDepositCreation;
import com.github.kellyihyeon.stanceadmin.domain.eventdeposit.EventDepositTransaction;
import com.github.kellyihyeon.stanceadmin.infrastructure.repository.eventdeposit.EventDepositTransactionEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class EventDepositTransactionMapperImpl implements EventDepositTransactionMapper {

    @Override
    public EventDepositTransactionEntity toEntity(EventDepositTransaction domain) {
        return EventDepositTransactionEntity.create(
                domain.getEventId(),
                domain.getDepositorId(),
                domain.getAmount(),
                domain.getDepositDate(),
                domain.getDescription(),
                domain.getCreatedAt(),
                domain.getCreatorId()
        );
    }

    @Override
    public List<EventDepositTransaction> toDomains(EventDepositCreation serviceDto, Long loggedInId, LocalDateTime now) {
        List<EventDepositTransaction> result = new ArrayList<>();

        for (Long depositorId : serviceDto.getDepositorIds()) {
            result.add(EventDepositTransaction.createWithoutId(
                    serviceDto.getEventId(),
                    depositorId,
                    serviceDto.getAmount(),
                    serviceDto.getDepositDate(),
                    serviceDto.getDescription(),
                    loggedInId,
                    now
            ));
        }

        return result;
    }

}
