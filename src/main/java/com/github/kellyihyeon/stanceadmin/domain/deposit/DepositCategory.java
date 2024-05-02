package com.github.kellyihyeon.stanceadmin.domain.deposit;

import lombok.Getter;

@Getter
public enum DepositCategory {

    MEMBERSHIP_FEE("회비"),
    TEAM_PARTY_EXPENSE("회식비"),
    UNIFORM_EXPENSE("유니폼"),
    INTEREST("이자"),
    CASHBACK("캐시백");

    private final String displayName;

    DepositCategory(String displayName) {
        this.displayName = displayName;
    }
}
