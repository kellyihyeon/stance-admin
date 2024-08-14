package com.github.kellyihyeon.stanceadmin.domain.account;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Account {

    private Long id;

    private Boolean isDefault;

    private String accountNickname;

    private Double balance;

    private String accountHolder;

    private String accountNumber;

    private Bank bank;

    private LocalDate usageStartDate;

    private LocalDate usageEndDate;

    private AccountStatus accountStatus;

    private LocalDateTime createdAt;

    private Long creatorId;

    private LocalDateTime updatedAt;

    private Long updaterId;

    private Account(Long id, Boolean isDefault, String accountNickname, Double balance, String accountHolder, String accountNumber, Bank bank, LocalDate usageStartDate, AccountStatus accountStatus, LocalDateTime createdAt, Long creatorId) {
        this.id = id;
        this.isDefault = isDefault;
        this.accountNickname = accountNickname;
        this.balance = balance;
        this.accountHolder = accountHolder;
        this.accountNumber = accountNumber;
        this.bank = bank;
        this.usageStartDate = usageStartDate;
        this.accountStatus = accountStatus;
        this.createdAt = createdAt;
        this.creatorId = creatorId;
    }

    public static Account createWithId(Long id, Boolean isDefault, String accountNickname, Double balance, String accountHolder, String accountNumber, Bank bank, LocalDate usageStartDate, AccountStatus accountStatus, LocalDateTime createdAt, Long creatorId) {
        Objects.requireNonNull(id, "계좌 ID가 null 이어서는 안됩니다.");
        Objects.requireNonNull(isDefault, "기본 계좌 여부가 null 이어서는 안됩니다.");
        Objects.requireNonNull(accountNickname, "계좌 별칭이 null 이어서는 안됩니다.");
        Objects.requireNonNull(balance, "잔액이 null 이어서는 안됩니다.");
        Objects.requireNonNull(accountHolder, "계좌 소유주가 null 이어서는 안됩니다.");
        Objects.requireNonNull(accountNumber, "계좌번호가 null 이어서는 안됩니다.");
        Objects.requireNonNull(bank, "은행명이 null 이어서는 안됩니다.");
        Objects.requireNonNull(usageStartDate, "사용일이 null 이어서는 안됩니다.");
        Objects.requireNonNull(accountStatus, "계좌 상태가 null 이어서는 안됩니다.");
        Objects.requireNonNull(createdAt, "데이터 생성일이 null 이어서는 안됩니다.");
        Objects.requireNonNull(creatorId, "데이터 생성자 ID가 null 이어서는 안됩니다.");

        return new Account(
                id,
                isDefault,
                accountNickname,
                balance,
                accountHolder,
                accountNumber,
                bank,
                usageStartDate,
                accountStatus,
                createdAt,
                creatorId
        );
    }

    private Account(Boolean isDefault, String accountNickname, Double balance, String accountHolder, String accountNumber, Bank bank, LocalDate usageStartDate, AccountStatus accountStatus, LocalDateTime createdAt, Long creatorId) {
        this.isDefault = isDefault;
        this.accountNickname = accountNickname;
        this.balance = balance;
        this.accountHolder = accountHolder;
        this.accountNumber = accountNumber;
        this.bank = bank;
        this.usageStartDate = usageStartDate;
        this.accountStatus = accountStatus;
        this.createdAt = createdAt;
        this.creatorId = creatorId;
    }

    public static Account createWithoutId(Boolean isDefault, String accountNickname, Double balance, String accountHolder, String accountNumber, Bank bank, LocalDate usageStartDate, AccountStatus accountStatus, LocalDateTime createdAt, Long creatorId) {
        Objects.requireNonNull(isDefault, "기본 계좌 여부가 null 이어서는 안됩니다.");
        Objects.requireNonNull(accountNickname, "계좌 별칭이 null 이어서는 안됩니다.");
        Objects.requireNonNull(balance, "잔액이 null 이어서는 안됩니다.");
        Objects.requireNonNull(accountHolder, "계좌 소유주가 null 이어서는 안됩니다.");
        Objects.requireNonNull(accountNumber, "계좌번호가 null 이어서는 안됩니다.");
        Objects.requireNonNull(bank, "은행명이 null 이어서는 안됩니다.");
        Objects.requireNonNull(usageStartDate, "사용일이 null 이어서는 안됩니다.");
        Objects.requireNonNull(accountStatus, "계좌 상태가 null 이어서는 안됩니다.");
        Objects.requireNonNull(createdAt, "데이터 생성일이 null 이어서는 안됩니다.");
        Objects.requireNonNull(creatorId, "데이터 생성자 ID가 null 이어서는 안됩니다.");

        return new Account(
                isDefault,
                accountNickname,
                balance,
                accountHolder,
                accountNumber,
                bank,
                usageStartDate,
                accountStatus,
                createdAt,
                creatorId
        );
    }

    public void updateBalance(Long invalidAccountId, double balance) {
        if (!hasMatchingAccountId(invalidAccountId)) {
            throw new IllegalArgumentException("계좌의 ID가 일치하지 않습니다.");
        }

        this.balance = balance;
    }

    private boolean hasMatchingAccountId(Long invalidAccountId) {
        Objects.requireNonNull(invalidAccountId, "invalidAccountId가 null 이어서는 안됩니다.");
        return this.id.equals(invalidAccountId);
    }

    public boolean isActive() {
        return AccountStatus.ACTIVE.equals(this.accountStatus);
    }
}
