package com.github.kellyihyeon.stanceadmin.infrastructure.repository.accounttransaction;

import com.github.kellyihyeon.stanceadmin.infrastructure.querydsl.AccountTransactionProjection;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class AccountTransactionQueryRepositoryImpl implements AccountTransactionQueryRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<AccountTransactionProjection> findAccountTransactionWithJoins(PageRequest pageable) {

        return null;
    }
}
