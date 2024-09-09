package com.github.kellyihyeon.stanceadmin.infrastructure.repository.cardpayment;

import com.github.kellyihyeon.stanceadmin.infrastructure.entity.cardpayment.CardPaymentTransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaCardPaymentTransactionEntityRepository extends JpaRepository<CardPaymentTransactionEntity, Long> {
}
