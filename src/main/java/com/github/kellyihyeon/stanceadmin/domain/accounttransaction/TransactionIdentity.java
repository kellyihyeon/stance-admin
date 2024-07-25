package com.github.kellyihyeon.stanceadmin.domain.accounttransaction;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TransactionIdentity {

    private Long transactionId;

    private TransactionType type;

    private TransactionSubType subtype;


    private TransactionIdentity(Long transactionId, TransactionType type, TransactionSubType subtype) {
        this.transactionId = transactionId;
        this.type = type;
        this.subtype = subtype;
    }

    public TransactionIdentity create(Long transactionId, TransactionType type, TransactionSubType subtype) {
        Objects.requireNonNull(transactionId, "transactionId 가 null 이어선 안됩니다.");
        Objects.requireNonNull(type, "type 이 null 이어선 안됩니다.");
        Objects.requireNonNull(subtype, "subtype 이 null 이어선 안됩니다.");

        return new TransactionIdentity(transactionId, type, subtype);
    }
}
