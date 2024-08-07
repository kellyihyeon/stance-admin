package com.github.kellyihyeon.stanceadmin.application.account;

import com.github.kellyihyeon.stanceadmin.domain.account.Account;
import com.github.kellyihyeon.stanceadmin.infrastructure.repository.account.AccountEntity;
import org.springframework.stereotype.Component;

@Component
public class AccountMapperImpl implements AccountMapper {

    public AccountEntity toEntity(Account account) {
        return AccountEntity.create(
                account.getId(),
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

    public Account toDomain(AccountEntity accountEntity) {
        return Account.createWithId(
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
