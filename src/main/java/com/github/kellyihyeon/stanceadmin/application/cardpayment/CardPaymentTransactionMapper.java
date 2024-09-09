package com.github.kellyihyeon.stanceadmin.application.cardpayment;

import com.github.kellyihyeon.stanceadmin.application.cardpayment.dto.CardPaymentCreation;
import com.github.kellyihyeon.stanceadmin.domain.cardpayment.CardPaymentTransaction;

import java.time.LocalDateTime;

public interface CardPaymentTransactionMapper {

    CardPaymentTransaction toDomain(CardPaymentCreation serviceDto, Long loggedInId, LocalDateTime now);

}
