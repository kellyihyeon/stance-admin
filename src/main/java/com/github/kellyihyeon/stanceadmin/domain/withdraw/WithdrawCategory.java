package com.github.kellyihyeon.stanceadmin.domain.withdraw;

public enum WithdrawCategory {
    TRANSFER("일반이체"),
    CARD_PAYMENT("카드결제");

    private String displayName;

    WithdrawCategory(String displayName) {
        this.displayName = displayName;
    }
}
