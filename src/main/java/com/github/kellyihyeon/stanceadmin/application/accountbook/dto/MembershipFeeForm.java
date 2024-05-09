package com.github.kellyihyeon.stanceadmin.application.accountbook.dto;

import com.github.kellyihyeon.stanceadmin.application.member.dto.MemberIdAndName;
import com.github.kellyihyeon.stanceadmin.domain.member.MemberType;
import com.github.kellyihyeon.stanceadmin.domain.member.MembershipFeeType;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.Month;
import java.time.Year;
import java.util.List;

public record MembershipFeeForm(
        MemberType memberType,
        MembershipFeeType membershipFeeType,
        BigDecimal amount,
        Year dueYear,
        Month dueMonth,
        String depositDate,
        List<MemberIdAndName> members,
        String description) {

    @Builder
    public MembershipFeeForm {
    }

}
