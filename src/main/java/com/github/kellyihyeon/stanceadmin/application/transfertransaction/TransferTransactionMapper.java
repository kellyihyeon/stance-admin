package com.github.kellyihyeon.stanceadmin.application.transfertransaction;

import com.github.kellyihyeon.stanceadmin.application.transfertransaction.dto.TransferCreation;
import com.github.kellyihyeon.stanceadmin.domain.transfertransaction.TransferTransaction;
import com.github.kellyihyeon.stanceadmin.infrastructure.entity.trasfertransaction.TransferTransactionEntity;

import java.time.LocalDateTime;

public interface TransferTransactionMapper {

    TransferTransaction toDomain(TransferCreation serviceDto, Long loggedInId, LocalDateTime now);

    TransferTransactionEntity toEntity(TransferTransaction transferTransaction);

}
