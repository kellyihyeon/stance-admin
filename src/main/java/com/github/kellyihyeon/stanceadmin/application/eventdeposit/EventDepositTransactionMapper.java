package com.github.kellyihyeon.stanceadmin.application.eventdeposit;

import com.github.kellyihyeon.stanceadmin.domain.eventdeposit.EventDepositTransaction;
import com.github.kellyihyeon.stanceadmin.infrastructure.repository.eventdeposit.EventDepositTransactionEntity;

public class EventDepositTransactionMapper {

    public static EventDepositTransactionEntity toEntity(EventDepositTransaction domain) {
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
}
