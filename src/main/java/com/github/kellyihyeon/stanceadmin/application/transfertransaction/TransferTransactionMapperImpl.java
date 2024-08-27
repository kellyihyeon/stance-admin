package com.github.kellyihyeon.stanceadmin.application.transfertransaction;

import com.github.kellyihyeon.stanceadmin.application.transfertransaction.dto.TransferCreation;
import com.github.kellyihyeon.stanceadmin.domain.transfertransaction.TransferTransaction;
import com.github.kellyihyeon.stanceadmin.infrastructure.entity.trasfertransaction.TransferTransactionEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class TransferTransactionMapperImpl implements TransferTransactionMapper {

    @Override
    public TransferTransaction toDomain(TransferCreation serviceDto, Long loggedInId, LocalDateTime now) {
        return TransferTransaction.createWithoutId(
                serviceDto.expenseCategory(),
                serviceDto.recipientName(),
                serviceDto.bank(),
                serviceDto.recipientAccountNumber(),
                serviceDto.amount(),
                serviceDto.expenseDate(),
                serviceDto.description(),
                loggedInId,
                now
        );
    }

    @Override
    public TransferTransactionEntity toEntity(TransferTransaction transferTransaction) {
        return new TransferTransactionEntity();
    }

}
