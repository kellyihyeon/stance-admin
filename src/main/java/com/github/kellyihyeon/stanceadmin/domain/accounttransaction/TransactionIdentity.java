package com.github.kellyihyeon.stanceadmin.domain.accounttransaction;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TransactionIdentity {

    private Long transactionId;

    private TransactionType type;

    private TransactionSubType subtype;


    private TransactionIdentity(Long transactionId, TransactionType type, TransactionSubType subtype) {
    }

    public TransactionIdentity createDeposit(Long transactionId, TransactionType type, TransactionSubType subtype) {
        return null;
    }
}
