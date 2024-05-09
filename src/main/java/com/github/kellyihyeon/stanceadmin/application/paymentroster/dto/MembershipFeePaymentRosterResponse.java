package com.github.kellyihyeon.stanceadmin.application.paymentroster.dto;

import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDate;

public record MembershipFeePaymentRosterResponse(
        Long memberId,
        String memberName,
        LocalDate applicationDate,
        LocalDate dueDate,
        LocalDate depositDate,
        BigDecimal amount,
        Boolean checked
) {

    @Builder
    public MembershipFeePaymentRosterResponse(Long memberId, String memberName, LocalDate applicationDate, LocalDate dueDate, LocalDate depositDate, BigDecimal amount, Boolean checked) {
        this.memberId = memberId;
        this.memberName = memberName;
        this.applicationDate = applicationDate;
        this.dueDate = dueDate;
        this.depositDate = depositDate;
        this.amount = amount;
        this.checked = checked;
    }
}
