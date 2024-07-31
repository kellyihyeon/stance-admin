package com.github.kellyihyeon.stanceadmin.application.eventdeposit;

import com.github.kellyihyeon.stanceadmin.application.eventdeposit.dto.EventDepositCreation;
import com.github.kellyihyeon.stanceadmin.domain.eventdeposit.EventDepositTransaction;
import com.github.kellyihyeon.stanceadmin.infrastructure.repository.eventdeposit.EventDepositTransactionEntity;

import java.time.LocalDateTime;
import java.util.List;

public interface EventDepositTransactionMapper {

    EventDepositTransactionEntity toEntity(EventDepositTransaction domain);

    List<EventDepositTransaction> toDomains(EventDepositCreation serviceDto, Long loggedInId, LocalDateTime now);

}
