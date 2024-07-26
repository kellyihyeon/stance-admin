package com.github.kellyihyeon.stanceadmin.infrastructure.repository.membershipfeedeposit;

import com.github.kellyihyeon.stanceadmin.infrastructure.entity.membershipfeedeposit.MemberShipFeeDepositTransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaMembershipFeeDepositTransactionEntityRepository extends JpaRepository<MemberShipFeeDepositTransactionEntity, Long> {
}
