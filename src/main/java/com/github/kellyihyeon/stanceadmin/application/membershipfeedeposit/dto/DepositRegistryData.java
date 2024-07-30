package com.github.kellyihyeon.stanceadmin.application.membershipfeedeposit.dto;

import com.github.kellyihyeon.stanceadmin.domain.member.MemberStatus;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class DepositRegistryData {

    private Long memberId;

    private String memberName;

    private Double amount;

    private MemberStatus memberStatus;

    private LocalDate dueDate;

    private LocalDate depositDate;


    @QueryProjection
    public DepositRegistryData(Long memberId, String memberName, Double amount, MemberStatus memberStatus, LocalDate dueDate, LocalDate depositDate) {
        this.memberId = memberId;
        this.memberName = memberName;
        this.amount = amount;
        this.memberStatus = memberStatus;
        this.dueDate = dueDate;
        this.depositDate = depositDate;
    }
}
