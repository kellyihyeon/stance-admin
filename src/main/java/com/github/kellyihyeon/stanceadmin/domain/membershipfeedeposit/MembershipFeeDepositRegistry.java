package com.github.kellyihyeon.stanceadmin.domain.membershipfeedeposit;

import com.github.kellyihyeon.stanceadmin.domain.member.MemberStatus;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class MembershipFeeDepositRegistry {

    private Long memberId;

    private String memberName;

    private Double amount;

    private MemberStatus memberStatus;

    private boolean isDeposited;

    private LocalDate dueDate;

    private LocalDate depositDate;

    public static MembershipFeeDepositRegistry create(Long memberId, String memberName, Double amount, MemberStatus memberStatus, LocalDate dueDate, LocalDate depositDate) {
        return null;
    }
}
