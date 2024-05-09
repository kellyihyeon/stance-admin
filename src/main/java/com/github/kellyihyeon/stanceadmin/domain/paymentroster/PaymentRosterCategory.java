package com.github.kellyihyeon.stanceadmin.domain.paymentroster;

public enum PaymentRosterCategory {

    TEAM_PARTY_EXPENSE("회식비"),
    UNIFORM_EXPENSE("유니폼");

    private final String displayName;

    PaymentRosterCategory(String displayName) {
        this.displayName = displayName;
    }
}
