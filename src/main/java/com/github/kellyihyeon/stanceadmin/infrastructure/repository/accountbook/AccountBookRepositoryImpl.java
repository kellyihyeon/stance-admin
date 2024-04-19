package com.github.kellyihyeon.stanceadmin.infrastructure.repository.accountbook;


import com.github.kellyihyeon.stanceadmin.application.accountbook.dto.AccountBookResponse;
import com.github.kellyihyeon.stanceadmin.application.accountbook.dto.QAccountBookResponse;
import com.github.kellyihyeon.stanceadmin.domain.accountbook.AccountBookRepositoryCustom;
import com.github.kellyihyeon.stanceadmin.domain.accountbook.TransactionType;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

import static com.github.kellyihyeon.stanceadmin.domain.accountbook.QAccountBook.*;
import static com.github.kellyihyeon.stanceadmin.domain.deposit.QDeposit.deposit;
import static com.github.kellyihyeon.stanceadmin.domain.withdraw.QWithdraw.withdraw;

@Repository
@RequiredArgsConstructor
public class AccountBookRepositoryImpl implements AccountBookRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<AccountBookResponse> findAccountBookByTransactionDateBetweenOrderByTransactionDateDesc(LocalDate startDate, LocalDate endDate) {
        StringExpression description = new CaseBuilder()
                .when(accountBook.transactionType.eq(TransactionType.DEPOSIT))
                .then(deposit.description)
                .otherwise(withdraw.description);

        return queryFactory
                .select(new QAccountBookResponse(
                        accountBook.id,
                        accountBook.transactionType,
                        accountBook.balance,
                        accountBook.transactionDate,
                        accountBook.transactionParty,
                        accountBook.amount,
                        description
                ))
                .from(accountBook)
                .leftJoin(deposit)
                .on(
                        accountBook.transactionType.eq(TransactionType.DEPOSIT)
                                .and(deposit.id.eq(accountBook.transactionId))

                )
                .leftJoin(withdraw)
                .on(
                        accountBook.transactionType.eq(TransactionType.WITHDRAW)
                                .and(withdraw.id.eq(accountBook.transactionId))
                )
                .where(accountBook.transactionDate.between(startDate, endDate))
                .orderBy(accountBook.id.desc())
                .fetch();
    }

}
