package com.github.kellyihyeon.stanceadmin.application.account;

import com.github.kellyihyeon.stanceadmin.domain.account.Account;
import com.github.kellyihyeon.stanceadmin.infrastructure.repository.account.AccountEntity;

public interface AccountMapper {

    AccountEntity toEntity(Account account);

    Account toDomain(AccountEntity accountEntity);
}
