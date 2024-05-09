package com.github.kellyihyeon.stanceadmin.application.deposit;

import com.github.kellyihyeon.stanceadmin.application.accountbook.dto.MembershipFeeByGuest;
import com.github.kellyihyeon.stanceadmin.application.accountbook.dto.MembershipFeeForm;
import com.github.kellyihyeon.stanceadmin.application.deposit.dto.*;
import com.github.kellyihyeon.stanceadmin.application.member.dto.MemberIdAndName;
import com.github.kellyihyeon.stanceadmin.domain.accountbook.AccountBook;
import com.github.kellyihyeon.stanceadmin.domain.accountbook.TransactionType;
import com.github.kellyihyeon.stanceadmin.domain.deposit.Deposit;
import com.github.kellyihyeon.stanceadmin.domain.deposit.DepositCategory;
import com.github.kellyihyeon.stanceadmin.domain.member.MemberType;
import com.github.kellyihyeon.stanceadmin.domain.member.MembershipFeeType;
import com.github.kellyihyeon.stanceadmin.infrastructure.repository.accountbook.AccountBookRepository;
import com.github.kellyihyeon.stanceadmin.infrastructure.repository.deposit.DepositRepository;
import com.github.kellyihyeon.stanceadmin.infrastructure.repository.member.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class DepositService {

    private final MemberRepository memberRepository;
    private final DepositRepository depositRepository;
    private final AccountBookRepository accountBookRepository;


    @Transactional
    public void registerMembershipFeeByMember(MembershipFeeForm membershipFeeForm) {
        List<Deposit> deposits = Deposit.toEntityList(membershipFeeForm);
        saveDeposits(deposits);
    }

    @Transactional
    public void registerMembershipFeeByGuest(MembershipFeeByGuest membershipFeeByGuest) {
        // TODO. 회원으로 등록하기
        List<MemberIdAndName> members = new LinkedList<>();
        members.add(new MemberIdAndName(998L, "coffee"));

        List<Deposit> deposits = Deposit.toEntityList(
                MembershipFeeForm.builder()
                        .memberType(MemberType.GUEST)
                        .membershipFeeType(MembershipFeeType.ONE_DAY)
                        .amount(membershipFeeByGuest.getAmount())
                        .dueYear(membershipFeeByGuest.getDueYear())
                        .dueMonth(membershipFeeByGuest.getDueMonth())
                        .depositDate(membershipFeeByGuest.getDepositDate())
                        .members(members)
                        .description(membershipFeeByGuest.getDescription())
                        .build()
        );

        saveDeposits(deposits);
    }

    public void registerExtraFeeByMember(ExtraFee extraFee) {
        List<Deposit> deposits = Deposit.toEntityList(extraFee);
        saveDeposits(deposits);
    }

    private void saveDeposits(List<Deposit> deposits) {
        for (Deposit deposit : deposits) {
            depositRepository.save(deposit);
            updateBalance(deposit);
        }
    }

    // TODO. Refactoring 대상
    private void updateBalance(Deposit deposit) {
        AccountBook latestAccountBook = accountBookRepository.findTopByOrderByIdDesc().orElseThrow(() -> new IllegalStateException("입출금 데이터가 없습니다."));
        log.debug("스탠스 가계부 최신 데이터 [{}]의 잔액 [{}]", latestAccountBook.getId(), latestAccountBook.getBalance());

        AccountBook updatedAccountBook = latestAccountBook.updateBalance(
                deposit.getId(),
                TransactionType.DEPOSIT,
                deposit.getDepositDate(),
                deposit.getDepositor(),
                deposit.getAmount()
        );

        accountBookRepository.save(updatedAccountBook);
    }

    public void registerCashByBank(CashFormByBank cashFormByBank) {
        Deposit deposit = cashFormByBank.toEntity();
        depositRepository.save(deposit);
        updateBalance(deposit);
    }

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

    public TopDepositCategoriesResponse getTopDepositsByCategoryTotalSum(Year year, Month month) {
        LocalDate firstDate = LocalDate.of(year.getValue(), month.getValue(), 1);
        LocalDate lastDate = firstDate.withDayOfMonth(firstDate.lengthOfMonth());

        List<DepositCategoryHistoryProjection> depositLogs = depositRepository.findByDepositDateBetween(firstDate, lastDate);

        Map<DepositCategory, BigDecimal> totalSumByCategory = calculateTotalSumByCategory(depositLogs);
        List<DepositCategory> categories = orderByCategoryTotalSumDescending(totalSumByCategory);
        List<TopDeposit> topDeposits = extractTopDepositsByCategoryTotalSum(totalSumByCategory, categories);

        return new TopDepositCategoriesResponse(
                year,
                month.getValue(),
                calculateTotalSumOfAllCategories(totalSumByCategory),
                topDeposits
        );
    }

    private BigDecimal calculateTotalSumOfAllCategories(Map<DepositCategory, BigDecimal> totalSumByCategory) {
        BigDecimal total = BigDecimal.ZERO;

        for (BigDecimal totalSum : totalSumByCategory.values()) {
            total = total.add(totalSum);
        }

        return total;
    }

    private List<TopDeposit> extractTopDepositsByCategoryTotalSum(Map<DepositCategory, BigDecimal> totalSumByCategory, List<DepositCategory> categories) {
        List<TopDeposit> topDeposits = new ArrayList<>();
        int TOP_RANK_COUNT = 3;

        if (categories.size() < TOP_RANK_COUNT) {
            TOP_RANK_COUNT = categories.size();
        }

        for (int i = 0; i < TOP_RANK_COUNT; i++) {
            int rank = i + 1;
            DepositCategory category = categories.get(i);
            BigDecimal sum = totalSumByCategory.get(category);

            topDeposits.add(new TopDeposit(rank, category.getDisplayName(), sum));
        }

        return topDeposits;
    }

    private List<DepositCategory> orderByCategoryTotalSumDescending(Map<DepositCategory, BigDecimal> totalSumByCategory) {
        List<DepositCategory> categories = new ArrayList<>(totalSumByCategory.keySet());
        categories.sort(
                (category1, category2) ->
                        totalSumByCategory.get(category2).compareTo(totalSumByCategory.get(category1))
        );

        return categories;
    }

    private Map<DepositCategory, BigDecimal> calculateTotalSumByCategory(List<DepositCategoryHistoryProjection> depositCategoryHistoryLogs) {
        Map<DepositCategory, BigDecimal> sumByCategory = new HashMap<>();
        BigDecimal total = BigDecimal.ZERO;

        for (DepositCategoryHistoryProjection historyLog : depositCategoryHistoryLogs) {
            total = total.add(historyLog.getAmount());
            DepositCategory category = historyLog.getCategory();
            BigDecimal accumulatedAmount = sumByCategory.getOrDefault(category, BigDecimal.ZERO).add(historyLog.getAmount());

            sumByCategory.put(category, accumulatedAmount);
        }

        return sumByCategory;
    }
}
