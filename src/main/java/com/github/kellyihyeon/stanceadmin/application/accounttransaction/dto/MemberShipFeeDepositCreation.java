package com.github.kellyihyeon.stanceadmin.application.accounttransaction.dto;

import com.github.kellyihyeon.stanceadmin.domain.membershipfeedeposit.MembershipFeeDepositTransaction;
import com.github.kellyihyeon.stanceadmin.domain.member.MemberType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public record MemberShipFeeDepositCreation (
        List<Long> depositorIds,
        LocalDate depositDate,
        Double amount,
        LocalDate dueDate,
        MemberType memberType,
        String description
) {

    public List<MembershipFeeDepositTransaction> toDomain() {
        List<MembershipFeeDepositTransaction> transactions = new ArrayList<>();
        LocalDateTime now = LocalDateTime.now();
        Long loggedInId = 999L;

        for (Long depositorId : depositorIds) {
            transactions.add(
                    new MembershipFeeDepositTransaction(
                            depositorId,
                            depositDate,
                            amount,
                            dueDate,
                            memberType,
                            description,
                            now,
                            loggedInId
                    )
            );
        }

        return transactions;
    }

}
