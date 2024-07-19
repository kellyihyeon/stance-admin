package com.github.kellyihyeon.stanceadmin.infrastructure.repository.accounttransaction;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaAccountTransactionEntityRepository extends JpaRepository<AccountTransactionEntity, Long> {
}
