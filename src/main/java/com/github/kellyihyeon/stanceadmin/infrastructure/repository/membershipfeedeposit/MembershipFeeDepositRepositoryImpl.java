package com.github.kellyihyeon.stanceadmin.infrastructure.repository.membershipfeedeposit;

import com.github.kellyihyeon.stanceadmin.domain.member.Member;
import com.github.kellyihyeon.stanceadmin.domain.membershipfeedeposit.MembershipFeeDepositRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MembershipFeeDepositRepositoryImpl implements MembershipFeeDepositRepository {

    private final JpaMembershipFeeDepositTransactionEntityRepository jpaRepository;


    @Override
    public List<Member> findPaidMembers(LocalDate startDate, LocalDate endDate) {
        return null;
    }
}
