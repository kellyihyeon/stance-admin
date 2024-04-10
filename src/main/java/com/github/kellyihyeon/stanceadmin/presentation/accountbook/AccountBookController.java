package com.github.kellyihyeon.stanceadmin.presentation.accountbook;

import com.github.kellyihyeon.stanceadmin.application.AccountBookService;
import com.github.kellyihyeon.stanceadmin.application.dto.MembershipFeeByMember;
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
            @RequestBody MembershipFeeByMember membershipFeeByMember
    ) {
        accountBookService.registerMembershipFeeByMember(membershipFeeByMember);
    }

}
