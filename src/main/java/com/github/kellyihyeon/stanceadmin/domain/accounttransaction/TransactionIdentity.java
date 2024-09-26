package com.github.kellyihyeon.stanceadmin.domain.accounttransaction;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Objects;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TransactionIdentity {

    private Long transactionId;

    private TransactionType type;

    private TransactionSubType subtype;

    private LocalDate transactionDate;

    private TransactionIdentity(Long transactionId, TransactionType type, TransactionSubType subtype, LocalDate transactionDate) {
        this.transactionId = transactionId;
        this.type = type;
        this.subtype = subtype;
        this.transactionDate = transactionDate;
    }

    public static TransactionIdentity create(Long transactionId, TransactionType type, TransactionSubType subtype, LocalDate transactionDate) {
        Objects.requireNonNull(transactionId, "transactionId 가 null 이어선 안됩니다.");
        Objects.requireNonNull(type, "type 이 null 이어선 안됩니다.");
        Objects.requireNonNull(subtype, "subtype 이 null 이어선 안됩니다.");
        Objects.requireNonNull(transactionDate, "transactionDate 가 null 이어선 안됩니다.");

        return new TransactionIdentity(transactionId, type, subtype, transactionDate);
    }
}
