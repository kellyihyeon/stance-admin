package com.github.kellyihyeon.stanceadmin.domain.accounttransaction;

import lombok.Getter;

@Getter
public class AccountTransactionSaved {

    private final Long accountId;
    private final Double balance;

    public AccountTransactionSaved(Long accountId, Double balance) {
        this.accountId = accountId;
        this.balance = balance;
    }
}
