package com.github.kellyihyeon.stanceadmin.infrastructure.repository.bankdeposit;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaBankDepositTransactionEntityRepository extends JpaRepository<BankDepositTransactionEntity, Long> {
}
