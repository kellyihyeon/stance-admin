package com.github.kellyihyeon.stanceadmin.application.cardpayment;

import com.github.kellyihyeon.stanceadmin.application.cardpayment.dto.CardPaymentCreation;
import com.github.kellyihyeon.stanceadmin.domain.cardpayment.CardPaymentTransaction;
import com.github.kellyihyeon.stanceadmin.infrastructure.entity.cardpayment.CardPaymentTransactionEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class CardPaymentTransactionMapperImpl implements CardPaymentTransactionMapper {

    @Override
    public CardPaymentTransaction toDomain(CardPaymentCreation serviceDto, Long loggedInId, LocalDateTime now) {
        return CardPaymentTransaction.create(
                serviceDto.cardHolderId(),
                serviceDto.expenseCategory(),
                serviceDto.cardUsageLocation(),
                serviceDto.amount(),
                serviceDto.expenseDate(),
                serviceDto.description(),
                loggedInId,
                now
        );
    }

    @Override
    public CardPaymentTransactionEntity toEntity(CardPaymentTransaction cardPaymentTransaction) {
        return CardPaymentTransactionEntity.create(
                cardPaymentTransaction.getCardHolderId(),
                cardPaymentTransaction.getExpenseCategory(),
                cardPaymentTransaction.getCardUsageLocation(),
                cardPaymentTransaction.getAmount(),
                cardPaymentTransaction.getExpenseDate(),
                cardPaymentTransaction.getDescription(),
                cardPaymentTransaction.getCreatorId(),
                cardPaymentTransaction.getCreatedAt()
        );
    }
}
