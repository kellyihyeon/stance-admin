package com.github.kellyihyeon.stanceadmin.domain.membershipfeedeposit;

import com.github.kellyihyeon.stanceadmin.domain.member.MemberStatus;
import lombok.Getter;

import java.time.LocalDate;
import java.util.Objects;

@Getter
public class MembershipFeeDepositRegistry {

    private Long memberId;

    private String memberName;

    private Double amount;

    private MemberStatus memberStatus;

    private boolean isDeposited;

    private LocalDate dueDate;

    private LocalDate depositDate;


    private MembershipFeeDepositRegistry(Long memberId, String memberName, Double amount, MemberStatus memberStatus, LocalDate dueDate, LocalDate depositDate) {
        this.memberId = memberId;
        this.memberName = memberName;
        this.amount = amount;
        this.memberStatus = memberStatus;
        this.dueDate = dueDate;
        this.depositDate = depositDate;
    }

    public static MembershipFeeDepositRegistry create(Long memberId, String memberName, Double amount, MemberStatus memberStatus, LocalDate dueDate, LocalDate depositDate) {
        Objects.requireNonNull(memberId, "memberId 가 null 이어선 안됩니다.");
        Objects.requireNonNull(memberName, "memberName 이 null 이어선 안됩니다.");
        Objects.requireNonNull(amount, "amount 가 null 이어선 안됩니다.");
        Objects.requireNonNull(memberStatus, "memberStatus 가 null 이어선 안됩니다.");
        Objects.requireNonNull(dueDate, "dueDate 가 null 이어선 안됩니다.");
        Objects.requireNonNull(depositDate, "depositDate 가 null 이어선 안됩니다.");

        return new MembershipFeeDepositRegistry(
                memberId,
                memberName,
                amount,
                memberStatus,
                dueDate,
                depositDate
        );
    }
}
