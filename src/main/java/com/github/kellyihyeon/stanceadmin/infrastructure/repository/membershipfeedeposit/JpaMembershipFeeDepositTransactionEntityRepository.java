package com.github.kellyihyeon.stanceadmin.infrastructure.repository.membershipfeedeposit;

import com.github.kellyihyeon.stanceadmin.infrastructure.entity.membershipfeedeposit.MemberShipFeeDepositTransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface JpaMembershipFeeDepositTransactionEntityRepository extends JpaRepository<MemberShipFeeDepositTransactionEntity, Long> {

    List<MemberShipFeeDepositTransactionEntity> findByDueDateBetween(LocalDate startDate, LocalDate endDate);
}
