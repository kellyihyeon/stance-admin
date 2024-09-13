package com.github.kellyihyeon.stanceadmin.domain.accounttransaction;

import lombok.Getter;

@Getter
public enum TransactionSubType {
    MEMBERSHIP_FEE("회비"),
    EVENT("이벤트"),
    BANK("은행"),
    CARD_PAYMENT("카드 결제"),
    TRANSFER("계좌 이체");

    private final String displayName;

    TransactionSubType(String displayName) {
        this.displayName = displayName;
    }

    public static String toDisplayName(String name) {
        for (TransactionSubType subType : TransactionSubType.values()) {
            if (subType.name().equalsIgnoreCase(name)) {
                return subType.getDisplayName();
            }
        }

        return null;
    }
}
