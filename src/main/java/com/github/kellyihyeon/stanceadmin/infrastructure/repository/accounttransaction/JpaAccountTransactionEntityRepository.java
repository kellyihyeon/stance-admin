package com.github.kellyihyeon.stanceadmin.infrastructure.repository.accounttransaction;

import com.github.kellyihyeon.stanceadmin.infrastructure.entity.accounttransaction.AccountTransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaAccountTransactionEntityRepository extends JpaRepository<AccountTransactionEntity, Long> {

    AccountTransactionEntity findFirstByOrderByCreatedAtDesc();
}
