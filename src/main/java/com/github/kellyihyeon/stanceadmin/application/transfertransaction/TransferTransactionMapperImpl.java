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
        return TransferTransactionEntity.create(
                transferTransaction.getExpenseCategory(),
                transferTransaction.getRecipientName(),
                transferTransaction.getBank(),
                transferTransaction.getRecipientAccountNumber(),
                transferTransaction.getAmount(),
                transferTransaction.getExpenseDate(),
                transferTransaction.getDescription(),
                transferTransaction.getCreatedAt(),
                transferTransaction.getCreatorId()
        );
    }

    @Override
    public TransferTransaction toDomain(TransferTransactionEntity entity) {
        return TransferTransaction.createWithId(
                entity.getId(),
                entity.getExpenseCategory(),
                entity.getRecipientName(),
                entity.getBank(),
                entity.getRecipientAccountNumber(),
                entity.getAmount(),
                entity.getExpenseDate(),
                entity.getDescription(),
                entity.getCreatedAt(),
                entity.getCreatorId(),
                entity.getUpdatedAt(),
                entity.getUpdaterId()
        );
    }

}
