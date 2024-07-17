package com.github.kellyihyeon.stanceadmin.application.accounttransaction.dto;

import com.github.kellyihyeon.stanceadmin.domain.accounttransaction.MembershipFeeDepositTransaction;
import com.github.kellyihyeon.stanceadmin.domain.member.MemberType;

import java.time.LocalDate;
import java.util.List;

public record MemberShipFeeDepositCreation (
        List<Long> depositorIds,
        LocalDate depositDate,
        Double amount,
        LocalDate dueDate,
        MemberType memberType,
        String description
) {

    public MembershipFeeDepositTransaction toDomain() {
        return null;
    }

}
