package com.github.kellyihyeon.stanceadmin.domain.account;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

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

    public Account(Boolean isDefault, String accountNickname, Double balance, String accountHolder, String accountNumber, Bank bank, LocalDate usageStartDate, AccountStatus accountStatus, LocalDateTime createdAt, Long creatorId) {
        requireNonNull(isDefault, "'기본 계좌 여부' 는 null 이어서는 안됩니다.");
        requireNonNull(accountNickname, "'계좌 별칭' 은 null 이어서는 안됩니다.");
        requireNonNull(balance, "'잔액' 은 null 이어서는 안됩니다.");
        requireNonNull(accountHolder, "'계좌 소유주' 는 null 이어서는 안됩니다.");
        requireNonNull(accountNumber, "'계좌번호' 는 null 이어서는 안됩니다.");
        requireNonNull(bank, "'은행명' 은 null 이어서는 안됩니다.");
        requireNonNull(usageStartDate, "'사용일' 은 null 이어서는 안됩니다.");
        requireNonNull(accountStatus, "'계좌 상태' 는 null 이어서는 안됩니다.");
        requireNonNull(createdAt, "'데이터 생성일' 은 null 이어서는 안됩니다.");
        requireNonNull(creatorId, "'데이터 생성자 ID' 는 null 이어서는 안됩니다.");
    }

    private <T> T requireNonNull(T obj, String message) {
        if (Objects.isNull(obj)) {
            throw new IllegalArgumentException(message);
        }
        return obj;
    }
}
