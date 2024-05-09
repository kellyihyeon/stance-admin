package com.github.kellyihyeon.stanceadmin.application.accountbook.dto;

import com.github.kellyihyeon.stanceadmin.application.member.dto.MemberIdAndName;
import com.github.kellyihyeon.stanceadmin.domain.member.MemberType;
import com.github.kellyihyeon.stanceadmin.domain.member.MembershipFeeType;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.Month;
import java.util.List;

@Getter
@AllArgsConstructor
public class MembershipFeeForm {

    private final MemberType memberType;

    private final MembershipFeeType membershipFeeType;

    private final BigDecimal amount;

    private final Month dueMonth;

    private final String depositDate;

    private final List<MemberIdAndName> members;

    private final String description;
}
