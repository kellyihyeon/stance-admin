package com.github.kellyihyeon.stanceadmin.application.membershipfeedeposit;

import com.github.kellyihyeon.stanceadmin.application.accounttransaction.AccountTransactionService;
import com.github.kellyihyeon.stanceadmin.application.accounttransaction.dto.MemberShipFeeDepositCreation;
import com.github.kellyihyeon.stanceadmin.application.member.MemberService;
import com.github.kellyihyeon.stanceadmin.application.member.dto.MemberSummaryResponse;
import com.github.kellyihyeon.stanceadmin.application.membershipfeedeposit.dto.DepositDateCondition;
import com.github.kellyihyeon.stanceadmin.domain.accounttransaction.TransactionIdentity;
import com.github.kellyihyeon.stanceadmin.domain.accounttransaction.TransactionSubType;
import com.github.kellyihyeon.stanceadmin.domain.accounttransaction.TransactionType;
import com.github.kellyihyeon.stanceadmin.domain.eventapplicantregistry.DepositStatus;
import com.github.kellyihyeon.stanceadmin.domain.member.Member;
import com.github.kellyihyeon.stanceadmin.domain.membershipfeedeposit.MembershipFeeDepositRegistry;
import com.github.kellyihyeon.stanceadmin.domain.membershipfeedeposit.MembershipFeeDepositRepository;
import com.github.kellyihyeon.stanceadmin.domain.membershipfeedeposit.MembershipFeeDepositTransaction;
import com.github.kellyihyeon.stanceadmin.models.DepositRateResponse;
import com.github.kellyihyeon.stanceadmin.models.MembershipFeePayerResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MembershipFeeDepositTransactionService {

    private final MembershipFeeDepositRepository repository;
    private final MemberService memberService;

    private final AccountTransactionService accountTransactionService;

    public List<MembershipFeePayerResponse> getMembershipFeePayersByDepositStatus(DepositStatus status, DepositDateCondition depositDateCondition) {
        validateDepositDate(depositDateCondition);

        YearMonth yearMonth = YearMonth.of(depositDateCondition.year(), depositDateCondition.month());
        LocalDate startDate = yearMonth.atDay(1);
        LocalDate endDate = yearMonth.atEndOfMonth();

        List<MembershipFeeDepositRegistry> registries = repository.getDepositRegistries(startDate, endDate);
        List<MembershipFeeDepositRegistry> confirmedDepositRecords = checkDepositInformation(registries);

        return createRegistryByDepositStatus(confirmedDepositRecords, status);
    }

    private List<MembershipFeePayerResponse> createRegistryByDepositStatus(List<MembershipFeeDepositRegistry> confirmedDepositRecords, DepositStatus status) {
        return confirmedDepositRecords.stream()
                .filter(record -> status.equals(record.getDepositStatus()))
                .map(
                        record -> new MembershipFeePayerResponse(
                                record.getMemberId(),
                                record.getMemberName(),
                                Optional.ofNullable(record.getAmount())
                                        .orElse((double)0),
                                record.getMemberStatus().getDisplayName(),
                                record.getDepositStatus().getDisplayName(),
                                Optional.ofNullable(record.getDepositDate())
                                        .map(LocalDate::toString)
                                        .orElse("")

                        )
                )
                .collect(Collectors.toList());
    }

    private List<MembershipFeeDepositRegistry> checkDepositInformation(List<MembershipFeeDepositRegistry> registries) {
        for (MembershipFeeDepositRegistry registry : registries) {
            if (registry.isDepositInfoConfirmed()) {
                registry.complete();
            }
        }

        return registries;
    }

    @Transactional
    public void createMembershipFeeDepositTransaction(MemberShipFeeDepositCreation serviceDto) {
        List<MembershipFeeDepositTransaction> transactions = serviceDto.toDomain();

        for (MembershipFeeDepositTransaction domain : transactions) {
            Long transactionId = repository.createMembershipFeeDepositTransaction(domain);

            // TODO: repository 에 저장된 entity 의 deposit date 를 파라미터로 넘길 것
            accountTransactionService.saveAccountTransaction(
                    TransactionIdentity.create(
                            transactionId,
                            TransactionType.DEPOSIT,
                            TransactionSubType.MEMBERSHIP_FEE,
                            domain.getDepositDate()
                            ),
                    serviceDto.amount()
            );
        }
    }

    public DepositRateResponse getDepositRate(DepositDateCondition depositDateCondition) {
        validateDepositDate(depositDateCondition);

        YearMonth dueDate = YearMonth.of(depositDateCondition.year(), depositDateCondition.month() - 1);
        LocalDate startDate = dueDate.atDay(1);
        LocalDate endDate = dueDate.atEndOfMonth();

        List<Member> paidMembers = repository.findPaidMembers(startDate, endDate);
        List<MemberSummaryResponse> participatingMembers = memberService.getParticipatingMembers();

        int totalPaidMembers = paidMembers.size();
        int totalParticipatingMembers = participatingMembers.size();
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
