package com.github.kellyihyeon.stanceadmin.infrastructure.repository.cardpayment;

import com.github.kellyihyeon.stanceadmin.application.cardpayment.CardPaymentTransactionMapper;
import com.github.kellyihyeon.stanceadmin.domain.cardpayment.CardPaymentTransaction;
import com.github.kellyihyeon.stanceadmin.domain.cardpayment.CardPaymentTransactionRepository;
import com.github.kellyihyeon.stanceadmin.infrastructure.entity.cardpayment.CardPaymentTransactionEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CardPaymentTransactionRepositoryImpl implements CardPaymentTransactionRepository {

    private final JpaCardPaymentTransactionEntityRepository jpaRepository;
    private final CardPaymentTransactionMapper mapper;

    @Override
    public CardPaymentTransaction save(CardPaymentTransaction cardPaymentTransaction) {
        CardPaymentTransactionEntity entity = jpaRepository.save(mapper.toEntity(cardPaymentTransaction));
        return mapper.toDomain(entity);
    }
}
