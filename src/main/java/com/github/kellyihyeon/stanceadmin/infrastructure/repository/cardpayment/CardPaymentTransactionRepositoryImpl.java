package com.github.kellyihyeon.stanceadmin.infrastructure.repository.cardpayment;

import com.github.kellyihyeon.stanceadmin.domain.cardpayment.CardPaymentTransaction;
import com.github.kellyihyeon.stanceadmin.domain.cardpayment.CardPaymentTransactionRepository;
import org.springframework.stereotype.Repository;

@Repository
public class CardPaymentTransactionRepositoryImpl implements CardPaymentTransactionRepository {

    @Override
    public CardPaymentTransaction save(CardPaymentTransaction cardPaymentTransaction) {
        return null;
    }
}
