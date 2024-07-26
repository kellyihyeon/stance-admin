package com.github.kellyihyeon.stanceadmin.application.membershipfeedeposit;

import com.github.kellyihyeon.stanceadmin.application.membershipfeedeposit.dto.DepositDateCondition;
import com.github.kellyihyeon.stanceadmin.domain.member.Member;
import com.github.kellyihyeon.stanceadmin.domain.membershipfeedeposit.MembershipFeeDepositRepository;
import com.github.kellyihyeon.stanceadmin.models.DepositRateResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MembershipFeeDepositTransactionService {

    private final MembershipFeeDepositRepository repository;

    public List<DepositRateResponse> getDepositRate(DepositDateCondition depositDateCondition) {
        validateDepositDate(depositDateCondition);

        YearMonth yearMonth = YearMonth.of(depositDateCondition.year(), depositDateCondition.month());
        LocalDate startDate = yearMonth.atDay(1);
        LocalDate endDate = yearMonth.atEndOfMonth();

        List<Member> paidMembers = repository.findPaidMembers(startDate, endDate);

        return null;
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
