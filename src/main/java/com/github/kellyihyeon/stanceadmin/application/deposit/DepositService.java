package com.github.kellyihyeon.stanceadmin.application.deposit;

import com.github.kellyihyeon.stanceadmin.application.deposit.dto.*;
import com.github.kellyihyeon.stanceadmin.domain.deposit.DepositCategory;
import com.github.kellyihyeon.stanceadmin.domain.member.MemberType;
import com.github.kellyihyeon.stanceadmin.infrastructure.repository.deposit.DepositRepository;
import com.github.kellyihyeon.stanceadmin.infrastructure.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.*;

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

    public TopDepositCategoriesResponse getDepositCategoriesHistory(Year year, Month month) {
        LocalDate firstDate = LocalDate.of(year.getValue(), month.getValue(), 1);
        LocalDate lastDate = firstDate.withDayOfMonth(firstDate.lengthOfMonth());

        // 1. 입금 내역 전체 로그
        List<DepositCategoryHistoryProjection> depositCategoryHistoryLogs = depositRepository.findByDepositDateBetween(firstDate, lastDate);

        Map<DepositCategory, BigDecimal> sumByCategory = new HashMap<>();
        BigDecimal total = BigDecimal.ZERO;

        for (DepositCategoryHistoryProjection historyLog : depositCategoryHistoryLogs) {
            total = total.add(historyLog.getAmount());
            DepositCategory category = historyLog.getCategory();
            BigDecimal accumulatedAmount = sumByCategory.getOrDefault(category, BigDecimal.ZERO).add(historyLog.getAmount());

            sumByCategory.put(category, accumulatedAmount);
        }

        // 2. 누적금액 별 내림차순
        List<DepositCategory> categories = new ArrayList<>(sumByCategory.keySet());
        categories.sort(
                (category1, category2) ->
                        sumByCategory.get(category2).compareTo(sumByCategory.get(category1))
        );

        // 3. 응답값 생성
        List<TopDeposit> topDeposits = new ArrayList<>();
        for (int i = 0; i < categories.size(); i++) {
            int rank = i + 1;
            DepositCategory category = categories.get(i);
            BigDecimal sum = sumByCategory.get(category);

            topDeposits.add(new TopDeposit(rank, category.getDisplayName(), sum));
        }

        return new TopDepositCategoriesResponse(year, month.getValue(), total, topDeposits);
    }
}
