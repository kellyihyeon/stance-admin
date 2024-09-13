package com.github.kellyihyeon.stanceadmin.domain.accounttransaction;

import lombok.Getter;

@Getter
public enum TransactionType {
    DEPOSIT("입금"),
    WITHDRAW("출금");

    private String displayName;

    TransactionType(String displayName) {
        this.displayName = displayName;
    }
}