package com.github.kellyihyeon.stanceadmin.application.paymentroster;

import com.github.kellyihyeon.stanceadmin.application.deposit.dto.Depositor;
import com.github.kellyihyeon.stanceadmin.application.paymentroster.dto.MembershipFeePaymentRosterResponse;
import com.github.kellyihyeon.stanceadmin.application.paymentroster.dto.PaymentRosterForm;
import com.github.kellyihyeon.stanceadmin.domain.deposit.DepositCategory;
import com.github.kellyihyeon.stanceadmin.domain.paymentroster.PaymentRoster;
import com.github.kellyihyeon.stanceadmin.infrastructure.repository.paymentroster.PaymentRosterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentRosterService {

    private final PaymentRosterRepository paymentRosterRepository;


    public void createPaymentRosters(PaymentRosterForm paymentRosterForm) {
        List<PaymentRoster> paymentRosters = new ArrayList<>();

        for (Long memberId : paymentRosterForm.memberIds()) {
            paymentRosters.add(
                    PaymentRoster.builder()
                            .category(paymentRosterForm.category())
                            .applicationDate(paymentRosterForm.applicationDate())
                            .dueDate(paymentRosterForm.dueDate())
                            .amount(paymentRosterForm.amount())
                            .memberId(memberId)
                            .creatorId(1L)
                            .createdDate(LocalDateTime.now())
                            .build());
        }

        paymentRosterRepository.saveAll(paymentRosters);
    }

    public List<MembershipFeePaymentRosterResponse> getMembershipFeePaymentRoster(Year year, Month month) {
        List<Depositor> depositors = paymentRosterRepository.findByCategoryAndDueYearAndDueMonth(
                DepositCategory.MEMBERSHIP_FEE,
                year,
                month
        );

        return checkMembershipFeePaid(depositors, year, month);
    }

    private List<MembershipFeePaymentRosterResponse> checkMembershipFeePaid(List<Depositor> depositors, Year year, Month month) {
        List<MembershipFeePaymentRosterResponse> paymentRosters = new ArrayList<>();

        LocalDate applicationDate = LocalDate.of(year.getValue(), month.getValue(), 1);
        LocalDate dueDate = applicationDate.withDayOfMonth(applicationDate.lengthOfMonth());

        boolean checked = true;
        for (Depositor depositor : depositors) {
            if (depositor.getId() == null) {
                checked = false;
            }

            paymentRosters.add(
                    MembershipFeePaymentRosterResponse.builder()
                            .memberId(depositor.getMemberId())
                            .memberName(depositor.getMemberName())
                            .applicationDate(applicationDate)
                            .dueDate(dueDate)
                            .depositDate(depositor.getDepositDate())
//                            .amount(depositor.getAmount())
                            .checked(checked)
                            .build());
        }

        return paymentRosters;
    }
}
