package com.github.kellyihyeon.stanceadmin.presentation.deposit;

import com.github.kellyihyeon.stanceadmin.application.deposit.DepositService;
import com.github.kellyihyeon.stanceadmin.application.deposit.dto.MembershipFeePaidMemberResponse;
import com.github.kellyihyeon.stanceadmin.application.deposit.dto.TopDepositCategoriesResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Month;
import java.time.Year;

@RestController
@RequiredArgsConstructor
@RequestMapping("/deposit")
public class DepositController {

    private final DepositService depositService;


    @GetMapping("/membership-fee/{year}/{month}")
    public ResponseEntity<MembershipFeePaidMemberResponse> getMembershipFeePaidMemberStatistics(
            @PathVariable Year year,
            @PathVariable Month month
    ) {
        MembershipFeePaidMemberResponse statistics = depositService.getMembershipFeePaidMemberStatistics(year, month);
        return ResponseEntity.ok(statistics);
    }

    @GetMapping("/categories/{year}/{month}")
    public ResponseEntity<TopDepositCategoriesResponse> getDepositCategoriesHistory(
            @PathVariable Year year,
            @PathVariable Month month
    ) {
        TopDepositCategoriesResponse topCategories = depositService.getDepositCategoriesHistory(year, month);
        return ResponseEntity.ok(topCategories);
    }
}
