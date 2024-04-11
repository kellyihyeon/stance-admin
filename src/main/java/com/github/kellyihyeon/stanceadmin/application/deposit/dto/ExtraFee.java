package com.github.kellyihyeon.stanceadmin.application.deposit.dto;

import com.github.kellyihyeon.stanceadmin.application.member.dto.MemberIdAndName;
import com.github.kellyihyeon.stanceadmin.domain.deposit.DepositCategory;

import java.util.List;

public record ExtraFee(
        DepositCategory depositCategory,
        Long amount,
        String depositDate,
        List<MemberIdAndName> members,
        String description
) { }
