package com.github.kellyihyeon.stanceadmin.application.accounttransaction;

import com.github.kellyihyeon.stanceadmin.domain.accounttransaction.AccountTransaction;
import com.github.kellyihyeon.stanceadmin.infrastructure.entity.accounttransaction.AccountTransactionEntity;

import java.util.List;

public interface AccountTransactionMapper {

    AccountTransactionEntity toEntity(AccountTransaction domain);

    AccountTransaction toDomain(AccountTransactionEntity entity);

    List<AccountTransaction> toDomains(List<AccountTransactionEntity> entities);

    List<AccountTransactionEntity> toEntities(List<AccountTransaction> domains);

}
