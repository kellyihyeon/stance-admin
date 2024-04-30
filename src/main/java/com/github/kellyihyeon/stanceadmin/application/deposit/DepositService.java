package com.github.kellyihyeon.stanceadmin.application.deposit;

import com.github.kellyihyeon.stanceadmin.application.deposit.dto.MembershipFeePaidMemberResponse;
import com.github.kellyihyeon.stanceadmin.domain.deposit.DepositCategory;
import com.github.kellyihyeon.stanceadmin.domain.member.MemberType;
import com.github.kellyihyeon.stanceadmin.infrastructure.repository.deposit.DepositRepository;
import com.github.kellyihyeon.stanceadmin.infrastructure.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Month;
import java.time.Year;
import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class DepositService {

    private final MemberRepository memberRepository;
    private final DepositRepository depositRepository;


    public MembershipFeePaidMemberResponse getMembershipFeePaidMemberStatistics(Year year, Month month) {
        Long regularMembers = memberRepository.countByMemberTypeIn(Arrays.asList(MemberType.ACTIVE, MemberType.INACTIVE));
        Long paidMembers = depositRepository.countByCategoryAndDueYearAndDueMonth(DepositCategory.MEMBERSHIP_FEE, year, month);

        Long paidMembersPercentage = calculatePaidMembersPercentage(regularMembers, paidMembers);

        return new MembershipFeePaidMemberResponse(regularMembers, paidMembers, paidMembersPercentage);
    }

    private Long calculatePaidMembersPercentage(Long regularMembers, Long paidMembers) {
        double percentage = Math.floor(((double) paidMembers / (double) regularMembers) * 100);
        return Double.valueOf(percentage).longValue();
    }
}
