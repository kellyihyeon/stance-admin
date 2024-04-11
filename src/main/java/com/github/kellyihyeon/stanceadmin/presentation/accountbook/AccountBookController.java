package com.github.kellyihyeon.stanceadmin.presentation.accountbook;

import com.github.kellyihyeon.stanceadmin.application.accountbook.AccountBookService;
import com.github.kellyihyeon.stanceadmin.application.accountbook.dto.MembershipFeeByGuest;
import com.github.kellyihyeon.stanceadmin.application.accountbook.dto.MembershipFeeForm;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account-book")
@RequiredArgsConstructor
public class AccountBookController {

    private final AccountBookService accountBookService;

    @PostMapping("/deposit/member/membership-fee")
    void depositMembershipFeeByMember(
            @RequestBody MembershipFeeForm membershipFeeForm
    ) {
        accountBookService.registerMembershipFeeByMember(membershipFeeForm);
    }

    @PostMapping("/deposit/guest/membership-fee")
    void depositMembershipFeeByGuest(
            @RequestBody MembershipFeeByGuest membershipFeeByGuest
    ) {
        accountBookService.registerMembershipFeeByGuest(membershipFeeByGuest);
    }
}
