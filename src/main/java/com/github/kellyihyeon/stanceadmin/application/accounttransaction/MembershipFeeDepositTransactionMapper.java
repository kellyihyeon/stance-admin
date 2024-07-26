package com.github.kellyihyeon.stanceadmin.application.accounttransaction;

import com.github.kellyihyeon.stanceadmin.domain.membershipfeedeposit.MembershipFeeDepositTransaction;
import com.github.kellyihyeon.stanceadmin.infrastructure.entity.membershipfeedeposit.MemberShipFeeDepositTransactionEntity;

public class MembershipFeeDepositTransactionMapper {

    public static MemberShipFeeDepositTransactionEntity toEntity(MembershipFeeDepositTransaction domain) {
        return MemberShipFeeDepositTransactionEntity.create(
                domain.getDepositorId(),
                domain.getDepositDate(),
                domain.getAmount(),
                domain.getDueDate(),
                domain.getMemberType(),
                domain.getDescription(),
                domain.getCreatedAt(),
                domain.getCreatorId()
        );
    }
}
