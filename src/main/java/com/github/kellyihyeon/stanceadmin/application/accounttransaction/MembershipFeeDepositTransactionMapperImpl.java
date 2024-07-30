package com.github.kellyihyeon.stanceadmin.application.accounttransaction;

import com.github.kellyihyeon.stanceadmin.domain.member.Member;
import com.github.kellyihyeon.stanceadmin.domain.membershipfeedeposit.MembershipFeeDepositRegistry;
import com.github.kellyihyeon.stanceadmin.domain.membershipfeedeposit.MembershipFeeDepositTransaction;
import com.github.kellyihyeon.stanceadmin.infrastructure.entity.member.MemberEntity;
import com.github.kellyihyeon.stanceadmin.infrastructure.entity.membershipfeedeposit.MemberShipFeeDepositTransactionEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MembershipFeeDepositTransactionMapperImpl implements MembershipFeeDepositTransactionMapper {

    @Override
    public MemberShipFeeDepositTransactionEntity toEntity(MembershipFeeDepositTransaction domain) {
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

    @Override
    public List<Member> toDomains(List<MemberShipFeeDepositTransactionEntity> entities) {
        return null;
    }

    @Override
    public MembershipFeeDepositRegistry toDomain(MemberEntity entity, MemberShipFeeDepositTransactionEntity memberShipFeeDepositTransactionEntity) {
        return null;
    }
}
