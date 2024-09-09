package com.github.kellyihyeon.stanceadmin.infrastructure.repository.transfertransaction;

import com.github.kellyihyeon.stanceadmin.infrastructure.entity.trasfertransaction.TransferTransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaTransferTransactionEntityRepository extends JpaRepository<TransferTransactionEntity, Long> {
}
