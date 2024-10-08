package com.github.kellyihyeon.stanceadmin.infrastructure.repository.account;

import com.github.kellyihyeon.stanceadmin.domain.account.Account;
import com.github.kellyihyeon.stanceadmin.domain.account.AccountStatus;
import com.github.kellyihyeon.stanceadmin.domain.account.Bank;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@ToString
@Table(name = "accounts")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "is_default", nullable = false)
    private Boolean isDefault;

    @Column(name = "account_nickname", nullable = false)
    private String accountNickname;

    @Column(name = "balance", nullable = false)
    private Double balance;

    @Column(name = "account_holder", nullable = false)
    private String accountHolder;

    @Column(name = "account_number", nullable = false)
    private String accountNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "bank", nullable = false)
    private Bank bank;

    @Column(name = "usage_start_date", nullable = false)
    private LocalDate usageStartDate;

    @Column(name = "usage_end_date")
    private LocalDate usageEndDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "account_status", nullable = false)
    private AccountStatus accountStatus;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "creator_id", nullable = false)
    private Long creatorId;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "updater_id")
    private Long updaterId;

    private AccountEntity(Long id, boolean isDefault, String accountNickname, Double balance, String accountHolder, String accountNumber, Bank bank, LocalDate usageStartDate, AccountStatus accountStatus, LocalDateTime createdAt, Long creatorId) {
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

    public static AccountEntity create(Long id, boolean isDefault, String accountNickname, Double balance, String accountHolder, String accountNumber, Bank bank, LocalDate usageStartDate, AccountStatus accountStatus, LocalDateTime createdAt, Long creatorId) {
        return new AccountEntity(
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

    public void updateBalance(Account account) {
        this.balance = account.getBalance();
        this.updatedAt = account.getUpdatedAt();
        this.updaterId = account.getUpdaterId();
    }
}
