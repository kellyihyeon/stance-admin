package com.github.kellyihyeon.stanceadmin.application.cardpayment;

import com.github.kellyihyeon.stanceadmin.application.cardpayment.dto.CardPaymentCreation;
import com.github.kellyihyeon.stanceadmin.domain.cardpayment.CardPaymentTransaction;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class CardPaymentTransactionMapperImpl implements CardPaymentTransactionMapper {

    @Override
    public CardPaymentTransaction toDomain(CardPaymentCreation serviceDto, Long loggedInId, LocalDateTime now) {
        return null;
    }
}
