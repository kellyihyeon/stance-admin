package com.github.kellyihyeon.stanceadmin.infrastructure.repository.accounttransaction;

import com.github.kellyihyeon.stanceadmin.infrastructure.querydsl.AccountTransactionProjection;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface AccountTransactionQueryRepository {

    List<AccountTransactionProjection> findAccountTransactionWithJoins(PageRequest pageable);

    long countTotalElements();
}
