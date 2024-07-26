package com.github.kellyihyeon.stanceadmin.domain.bankdeposit;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BankDepositTransaction {

    private Long id;

    private DepositType depositType;

    private Long depositorId;

    private String depositSource;

    private Double amount;

    private LocalDate depositDate;

    private LocalDateTime createdAt;

    private Long creatorId;

    private LocalDateTime updatedAt;

    private Long updaterId;

    public BankDepositTransaction(Long id, DepositType depositType, Long depositorId, Double amount, LocalDate depositDate) {
        this.id = id;
        this.depositType = depositType;
        this.depositorId = depositorId;
        this.amount = amount;
        this.depositDate = depositDate;
    }

    private BankDepositTransaction(DepositType type, String depositSource, Double amount, LocalDate depositDate, Long loggedInId, LocalDateTime now) {
        this.depositType = type;
        this.depositSource = depositSource;
        this.amount = amount;
        this.depositDate = depositDate;
        this.creatorId = loggedInId;
        this.createdAt = now;
    }

    private static BankDepositTransaction deposit(Long id, DepositType type, Long depositorId, Double amount, LocalDate depositDate) {
        Objects.requireNonNull(id, "id 가 null 이어선 안됩니다.");
        Objects.requireNonNull(type, "type 이 null 이어선 안됩니다.");
        Objects.requireNonNull(depositorId, "depositorId 가 null 이어선 안됩니다.");
        Objects.requireNonNull(amount, "amount 가 null 이어선 안됩니다.");
        Objects.requireNonNull(depositDate, "depositDate 가 null 이어선 안됩니다.");

        return new BankDepositTransaction(
                id,
                type,
                depositorId,
                amount,
                depositDate
        );
    }

    public static BankDepositTransaction create(DepositType type, String depositSource, Double amount, LocalDate depositDate, Long loggedInId, LocalDateTime now) {
        Objects.requireNonNull(type, "type 이 null 이어선 안됩니다.");
        Objects.requireNonNull(depositSource, "depositSource 가 null 이어선 안됩니다.");
        Objects.requireNonNull(amount, "amount 가 null 이어선 안됩니다.");
        Objects.requireNonNull(depositDate, "depositDate 가 null 이어선 안됩니다.");
        Objects.requireNonNull(loggedInId, "loggedInId 가 null 이어선 안됩니다.");
        Objects.requireNonNull(now, "now 가 null 이어선 안됩니다.");

        return new BankDepositTransaction(
                type,
                depositSource,
                amount,
                depositDate,
                loggedInId,
                now
        );
    }

    public BankDepositTransaction depositCashback(Long id, Long depositorId, Double amount, LocalDate depositDate) {
        return BankDepositTransaction.deposit(
                id,
                DepositType.CASHBACK,
                depositorId,
                amount,
                depositDate
        );
    }

    public BankDepositTransaction depositInterest(Long id, Long depositorId, Double amount, LocalDate depositDate) {
        return BankDepositTransaction.deposit(
                id,
                DepositType.INTEREST,
                depositorId,
                amount,
                depositDate
        );
    }
}
