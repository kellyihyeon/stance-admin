package com.github.kellyihyeon.stanceadmin.application.accounttransaction;

import com.github.kellyihyeon.stanceadmin.domain.member.Member;
import com.github.kellyihyeon.stanceadmin.domain.membershipfeedeposit.MembershipFeeDepositTransaction;
import com.github.kellyihyeon.stanceadmin.infrastructure.entity.membershipfeedeposit.MemberShipFeeDepositTransactionEntity;

import java.util.List;

public interface MembershipFeeDepositTransactionMapper {

    MemberShipFeeDepositTransactionEntity toEntity(MembershipFeeDepositTransaction domain);

    List<Member> toDomains(List<MemberShipFeeDepositTransactionEntity> entities);

}
