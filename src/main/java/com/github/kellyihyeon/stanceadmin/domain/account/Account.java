package com.github.kellyihyeon.stanceadmin.domain.account;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Account {

    private Long id;

    private boolean isDefault;

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

}
