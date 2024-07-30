package com.github.kellyihyeon.stanceadmin.domain.membershipfeedeposit;

import com.github.kellyihyeon.stanceadmin.domain.member.Member;

import java.time.LocalDate;
import java.util.List;

public interface MembershipFeeDepositRepository {

    List<Member> findPaidMembers(LocalDate startDate, LocalDate endDate);

    List<MembershipFeeDepositRegistry> getDepositRegistries(LocalDate startDate, LocalDate endDate);

    Long createMembershipFeeDepositTransaction(MembershipFeeDepositTransaction domain);
}
