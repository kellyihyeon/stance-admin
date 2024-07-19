package com.github.kellyihyeon.stanceadmin.application.account;

import com.github.kellyihyeon.stanceadmin.domain.account.Account;
import com.github.kellyihyeon.stanceadmin.infrastructure.repository.account.AccountEntity;

public class AccountMapper {

    public static AccountEntity toEntity(Account account) {
        return AccountEntity.create(
                account.getIsDefault(),
                account.getAccountNickname(),
                account.getBalance(),
                account.getAccountHolder(),
                account.getAccountNumber(),
                account.getBank(),
                account.getUsageStartDate(),
                account.getAccountStatus(),
                account.getCreatedAt(),
                account.getCreatorId()
        );
    }

    public static Account toDomain(AccountEntity accountEntity) {
        return new Account(
                accountEntity.getId(),
                accountEntity.getIsDefault(),
                accountEntity.getAccountNickname(),
                accountEntity.getBalance(),
                accountEntity.getAccountHolder(),
                accountEntity.getAccountNumber(),
                accountEntity.getBank(),
                accountEntity.getUsageStartDate(),
                accountEntity.getAccountStatus(),
                accountEntity.getCreatedAt(),
                accountEntity.getCreatorId()
        );
    }
}
