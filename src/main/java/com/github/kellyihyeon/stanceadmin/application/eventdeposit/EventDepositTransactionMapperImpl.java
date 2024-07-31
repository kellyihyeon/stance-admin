package com.github.kellyihyeon.stanceadmin.application.eventdeposit;

import com.github.kellyihyeon.stanceadmin.application.eventdeposit.dto.EventDepositCreation;
import com.github.kellyihyeon.stanceadmin.domain.eventdeposit.EventDepositTransaction;
import com.github.kellyihyeon.stanceadmin.infrastructure.repository.eventdeposit.EventDepositTransactionEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class EventDepositTransactionMapperImpl implements EventDepositTransactionMapper {

    @Override
    public EventDepositTransactionEntity toEntity(EventDepositTransaction domain) {
        return EventDepositTransactionEntity.create(
                domain.getEventId(),
                domain.getApplicantId(),
                domain.getAmount(),
                domain.getDepositDate(),
                domain.getDescription(),
                domain.getCreatedAt(),
                domain.getCreatorId()
        );
    }

    @Override
    public List<EventDepositTransaction> toDomains(EventDepositCreation serviceDto, Long loggedInId, LocalDateTime now) {
        return null;
    }

}
