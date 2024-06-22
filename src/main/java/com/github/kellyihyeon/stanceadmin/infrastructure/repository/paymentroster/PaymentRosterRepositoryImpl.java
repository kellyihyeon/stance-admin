package com.github.kellyihyeon.stanceadmin.infrastructure.repository.paymentroster;

import com.github.kellyihyeon.stanceadmin.application.deposit.dto.Depositor;
import com.github.kellyihyeon.stanceadmin.application.deposit.dto.QDepositor;
import com.github.kellyihyeon.stanceadmin.domain.deposit.DepositCategory;
import com.github.kellyihyeon.stanceadmin.domain.member.MemberType;
import com.github.kellyihyeon.stanceadmin.domain.paymentroster.PaymentRosterRepositoryCustom;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.Month;
import java.time.Year;
import java.util.List;

import static com.github.kellyihyeon.stanceadmin.domain.deposit.QDeposit.deposit;
import static com.github.kellyihyeon.stanceadmin.infrastructure.repository.member.QMemberEntity.memberEntity;

@Repository
@RequiredArgsConstructor
public class PaymentRosterRepositoryImpl implements PaymentRosterRepositoryCustom {

    private final JPAQueryFactory queryFactory;


    @Override
    public List<Depositor> findByCategoryAndDueYearAndDueMonth(
            DepositCategory category,
            Year year,
            Month month
    ) {

        return queryFactory
                .select(
                        new QDepositor(
                                deposit.id,
                                memberEntity.id,
                                memberEntity.name,
                                deposit.category,
                                deposit.amount,
                                deposit.dueYear,
                                deposit.dueMonth,
                                deposit.depositDate
                        )
                )
                .from(memberEntity)
                .leftJoin(deposit)
                .on(deposit.memberId.eq(memberEntity.id)
                        .and(deposit.category.eq(category))
                        .and(deposit.dueYear.eq(year))
                        .and(deposit.dueMonth.eq(month))
                )
                .where(
                        memberEntity.memberType.in(MemberType.ACTIVE, MemberType.INACTIVE)
                )
                .fetch();
    }

}
