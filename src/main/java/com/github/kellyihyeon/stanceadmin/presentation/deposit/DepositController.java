package com.github.kellyihyeon.stanceadmin.presentation.deposit;

import com.github.kellyihyeon.stanceadmin.application.accountbook.dto.MembershipFeeByGuest;
import com.github.kellyihyeon.stanceadmin.application.accountbook.dto.MembershipFeeForm;
import com.github.kellyihyeon.stanceadmin.application.deposit.DepositService;
import com.github.kellyihyeon.stanceadmin.application.deposit.dto.CashFormByBank;
import com.github.kellyihyeon.stanceadmin.application.deposit.dto.ExtraFee;
import com.github.kellyihyeon.stanceadmin.application.deposit.dto.MembershipFeePaidMemberResponse;
import com.github.kellyihyeon.stanceadmin.application.deposit.dto.TopDepositCategoriesResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Month;
import java.time.Year;

@RestController
@RequiredArgsConstructor
@RequestMapping("/deposits")
public class DepositController {

    private final DepositService depositService;

    @PostMapping("/members/membership-fee")
    void depositMembershipFeeByMember(
            @RequestBody MembershipFeeForm membershipFeeForm
    ) {
        depositService.registerMembershipFeeByMember(membershipFeeForm);
    }

    @PostMapping("/guests/membership-fee")
    void depositMembershipFeeByGuest(
            @RequestBody MembershipFeeByGuest membershipFeeByGuest
    ) {
        depositService.registerMembershipFeeByGuest(membershipFeeByGuest);
    }

    @PostMapping("/members/extra-fee")
    void depositExtraFeeByMember(
            @RequestBody ExtraFee extraFee
    ) {
        depositService.registerExtraFeeByMember(extraFee);
    }

    @PostMapping("/banks")
    void depositCashByBank(
            @RequestBody CashFormByBank cashFormByBank
    ) {
        depositService.registerCashByBank(cashFormByBank);
    }


    @GetMapping("/membership-fees/{year}/{month}")
    public ResponseEntity<MembershipFeePaidMemberResponse> getMembershipFeePaidMemberStatistics(
            @PathVariable Year year,
            @PathVariable Month month
    ) {
        MembershipFeePaidMemberResponse statistics = depositService.getMembershipFeePaidMemberStatistics(year, month);
        return ResponseEntity.ok(statistics);
    }

    @GetMapping("/categories/{year}/{month}")
    public ResponseEntity<TopDepositCategoriesResponse> getTopDepositsByCategoryTotalSum(
            @PathVariable Year year,
            @PathVariable Month month
    ) {
        TopDepositCategoriesResponse topCategories = depositService.getTopDepositsByCategoryTotalSum(year, month);
        return ResponseEntity.ok(topCategories);
    }
}
