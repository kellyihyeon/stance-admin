package com.github.kellyihyeon.stanceadmin.presentation.membershipfeedeposit;

import com.github.kellyihyeon.stanceadmin.apis.MembershipFeeDepositTransactionApi;
import com.github.kellyihyeon.stanceadmin.application.accounttransaction.dto.MemberShipFeeDepositCreation;
import com.github.kellyihyeon.stanceadmin.application.membershipfeedeposit.MembershipFeeDepositTransactionService;
import com.github.kellyihyeon.stanceadmin.application.membershipfeedeposit.dto.DepositDateCondition;
import com.github.kellyihyeon.stanceadmin.domain.member.MemberType;
import com.github.kellyihyeon.stanceadmin.models.DepositRateResponse;
import com.github.kellyihyeon.stanceadmin.models.MembershipFeeDepositInput;
import com.github.kellyihyeon.stanceadmin.models.MembershipFeePayerResponse;
import com.github.kellyihyeon.stanceadmin.presentation.TimeConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.net.URI;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MembershipFeeDepositTransactionController implements MembershipFeeDepositTransactionApi {

    private final MembershipFeeDepositTransactionService membershipFeeDepositService;

    @Override
    public ResponseEntity<List<MembershipFeePayerResponse>> getMembershipFeePayersByDepositStatus(String depositStatus) {

        return null;
    }

    @Override
    public ResponseEntity<Void> saveMembershipFeeDepositTransaction(MembershipFeeDepositInput input) {
        MemberShipFeeDepositCreation serviceDto = new MemberShipFeeDepositCreation(
                input.getDepositorIds(),
                TimeConverter.convertToLocalDate(input.getDepositDate()),
                input.getAmount(),
                TimeConverter.convertToLocalDate(input.getDueDate()),
                MemberType.valueOf(input.getMemberType().getValue()),
                input.getDescription()
        );

        membershipFeeDepositService.createMembershipFeeDepositTransaction(serviceDto);
        return ResponseEntity.created(URI.create("")).build();
    }

    @Override
    public ResponseEntity<DepositRateResponse> getDepositRate(Integer year, Integer month) {
        validateYear(year);
        validateMonth(month);

        DepositRateResponse result = membershipFeeDepositService.getDepositRate(new DepositDateCondition(year, month));
        return ResponseEntity.ok(result);
    }

    private void validateMonth(Integer month) {
        if (month < 1 || month > 12) {
            throw new IllegalArgumentException("month 는 1에서 12 사이 이어야 해요.");

        }
    }

    private void validateYear(Integer year) {
        if (year < 2023) {
            throw new IllegalArgumentException("year 를 2023년 이상으로 지정해주세요.");
        }
    }
}
