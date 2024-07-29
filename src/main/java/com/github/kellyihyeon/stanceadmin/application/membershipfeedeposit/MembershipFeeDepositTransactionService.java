package com.github.kellyihyeon.stanceadmin.application.membershipfeedeposit;

import com.github.kellyihyeon.stanceadmin.application.member.MemberService;
import com.github.kellyihyeon.stanceadmin.application.membershipfeedeposit.dto.DepositDateCondition;
import com.github.kellyihyeon.stanceadmin.domain.membershipfeedeposit.MembershipFeeDepositRepository;
import com.github.kellyihyeon.stanceadmin.models.DepositRateResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.YearMonth;

@Service
@RequiredArgsConstructor
public class MembershipFeeDepositTransactionService {

    private final MembershipFeeDepositRepository repository;
    private final MemberService memberService;

    public DepositRateResponse getDepositRate(DepositDateCondition depositDateCondition) {
        validateDepositDate(depositDateCondition);

        YearMonth yearMonth = YearMonth.of(depositDateCondition.year(), depositDateCondition.month());
        LocalDate startDate = yearMonth.atDay(1);
        LocalDate endDate = yearMonth.atEndOfMonth();

        int totalPaidMembers = repository.findPaidMembers(startDate, endDate).size();
        int totalParticipatingMembers = memberService.getParticipatingMembers().size();

        int depositRatePercentage = calculateDepositRate(totalPaidMembers, totalParticipatingMembers);

        return new DepositRateResponse(
                depositDateCondition.year(),
                depositDateCondition.month(),
                depositRatePercentage,
                totalParticipatingMembers,
                totalPaidMembers
        );
    }

    private int calculateDepositRate(int totalPaidMembers, int totalParticipatingMembers) {
        if (totalParticipatingMembers == 0) {
            throw new IllegalArgumentException("나누려고 하는 멤버의 수가 0이 되면 안돼요.");
        }

        double depositRate = (double) totalPaidMembers / totalParticipatingMembers;
        return (int) Math.floor(depositRate * 100);
    }

    private void validateDepositDate(DepositDateCondition depositDateCondition) {
        if (depositDateCondition.year() < 2023) {
            throw new IllegalArgumentException("year 를 2023년 이상으로 지정해주세요.");
        }

        if (depositDateCondition.month() < 1 || depositDateCondition.month() > 12) {
            throw new IllegalArgumentException("month 는 1에서 12 사이 이어야 해요.");
        }
    }
}
