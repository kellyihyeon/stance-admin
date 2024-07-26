package com.github.kellyihyeon.stanceadmin.domain.bankdeposit;

public enum DepositType {
    CASHBACK("캐시백"),
    INTEREST("이자")
    ;

    private String displayName;

    DepositType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
