package com.github.kellyihyeon.stanceadmin.domain.account;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class AccountBuilder {

    private Long id = 1L;

    private Boolean isDefault = true;

    private String accountNickname = "스탠스 모임통장";

    private Double balance = (double) 0;

    private String accountHolder = "강스탠스";

    private String accountNumber = "123456-09-098765";

    private Bank bank = Bank.KAKAO;

    private LocalDate usageStartDate = LocalDate.of(2024, 03, 01);

    private AccountStatus accountStatus = AccountStatus.ACTIVE;

    private LocalDateTime createdAt = LocalDateTime.now();

    private Long creatorId = 999L;

    public static AccountBuilder builder() {
        return new AccountBuilder();
    }

    public AccountBuilder id(Long id) {
        this.id = id;
        return this;
    }

    public AccountBuilder isDefault(Boolean isDefault) {
        this.isDefault = isDefault;
        return this;
    }

    public AccountBuilder accountNickname(String accountNickname) {
        this.accountNickname = accountNickname;
        return this;
    }

    public AccountBuilder balance(Double balance) {
        this.balance = balance;
        return this;
    }

    public AccountBuilder accountHolder(String accountHolder) {
        this.accountHolder = accountHolder;
        return this;
    }

    public AccountBuilder accountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
        return this;
    }

    public AccountBuilder bank(Bank bank) {
        this.bank = bank;
        return this;
    }

    public AccountBuilder usageStartDate(LocalDate usageStartDate) {
        this.usageStartDate = usageStartDate;
        return this;
    }

    public AccountBuilder accountStatus(AccountStatus accountStatus) {
        this.accountStatus = accountStatus;
        return this;
    }

    public AccountBuilder createdAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public AccountBuilder creatorId(Long creatorId) {
        this.creatorId = creatorId;
        return this;
    }

    public Account build() {
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
}

