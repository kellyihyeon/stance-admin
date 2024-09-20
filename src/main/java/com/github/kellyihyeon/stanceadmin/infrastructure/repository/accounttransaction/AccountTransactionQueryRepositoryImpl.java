package com.github.kellyihyeon.stanceadmin.infrastructure.repository.accounttransaction;

import com.github.kellyihyeon.stanceadmin.domain.accounttransaction.TransactionSubType;
import com.github.kellyihyeon.stanceadmin.infrastructure.querydsl.AccountTransactionProjection;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

import static com.github.kellyihyeon.stanceadmin.infrastructure.entity.accounttransaction.QAccountTransactionEntity.accountTransactionEntity;
import static com.github.kellyihyeon.stanceadmin.infrastructure.entity.cardpayment.QCardPaymentTransactionEntity.cardPaymentTransactionEntity;
import static com.github.kellyihyeon.stanceadmin.infrastructure.entity.event.QEventEntity.eventEntity;
import static com.github.kellyihyeon.stanceadmin.infrastructure.entity.member.QMemberEntity.memberEntity;
import static com.github.kellyihyeon.stanceadmin.infrastructure.entity.membershipfeedeposit.QMemberShipFeeDepositTransactionEntity.memberShipFeeDepositTransactionEntity;
import static com.github.kellyihyeon.stanceadmin.infrastructure.entity.trasfertransaction.QTransferTransactionEntity.transferTransactionEntity;
import static com.github.kellyihyeon.stanceadmin.infrastructure.repository.bankdeposit.QBankDepositTransactionEntity.bankDepositTransactionEntity;
import static com.github.kellyihyeon.stanceadmin.infrastructure.repository.eventdeposit.QEventDepositTransactionEntity.eventDepositTransactionEntity;

@Repository
@RequiredArgsConstructor
public class AccountTransactionQueryRepositoryImpl implements AccountTransactionQueryRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<AccountTransactionProjection> findAccountTransactionWithJoins(PageRequest pageable) {

        JPAQuery<AccountTransactionProjection> query =
                queryFactory
                        .select(
                                Projections.fields(
                                        AccountTransactionProjection.class,
                                        accountTransactionEntity.id,
                                        accountTransactionEntity.transactionId,
                                        accountTransactionEntity.transactionType,
                                        accountTransactionEntity.amount,
                                        accountTransactionEntity.balance,
                                        new CaseBuilder()
                                                .when(accountTransactionEntity.transactionSubType.eq(TransactionSubType.MEMBERSHIP_FEE))
                                                .then(memberEntity.name)
                                                .when(accountTransactionEntity.transactionSubType.eq(TransactionSubType.BANK))
                                                .then(bankDepositTransactionEntity.depositSource)
                                                .when(accountTransactionEntity.transactionSubType.eq(TransactionSubType.EVENT))
                                                .then(memberEntity.name)
                                                .when(accountTransactionEntity.transactionSubType.eq(TransactionSubType.CARD_PAYMENT))
                                                .then(cardPaymentTransactionEntity.cardUsageLocation)
                                                .when(accountTransactionEntity.transactionSubType.eq(TransactionSubType.TRANSFER))
                                                .then(transferTransactionEntity.recipientName)
                                                .otherwise(Expressions.constant("없음"))
                                                .as("transactionParty"),
                                        new CaseBuilder()
                                                .when(accountTransactionEntity.transactionSubType.eq(TransactionSubType.MEMBERSHIP_FEE))
                                                .then(memberShipFeeDepositTransactionEntity.depositDate)
                                                .when(accountTransactionEntity.transactionSubType.eq(TransactionSubType.BANK))
                                                .then(bankDepositTransactionEntity.depositDate)
                                                .when(accountTransactionEntity.transactionSubType.eq(TransactionSubType.EVENT))
                                                .then(eventDepositTransactionEntity.depositDate)
                                                .when(accountTransactionEntity.transactionSubType.eq(TransactionSubType.CARD_PAYMENT))
                                                .then(cardPaymentTransactionEntity.expenseDate)
                                                .when(accountTransactionEntity.transactionSubType.eq(TransactionSubType.TRANSFER))
                                                .then(transferTransactionEntity.expenseDate)
                                                .otherwise(LocalDate.of(1900,1,1))
                                                .as("transactionDate"),
                                        new CaseBuilder()
                                                .when(accountTransactionEntity.transactionSubType.eq(TransactionSubType.EVENT))
                                                .then(eventEntity.eventItem.stringValue())
                                                .otherwise(accountTransactionEntity.transactionSubType.stringValue())
                                                .as("detailType")
                                )

                        );

        query
                .from(accountTransactionEntity)
                .leftJoin(memberShipFeeDepositTransactionEntity)
                .on(accountTransactionEntity.transactionSubType.eq(TransactionSubType.MEMBERSHIP_FEE)
                        .and(memberShipFeeDepositTransactionEntity.id.eq(accountTransactionEntity.transactionId)))
                .leftJoin(bankDepositTransactionEntity)
                .on(accountTransactionEntity.transactionSubType.eq(TransactionSubType.BANK)
                        .and(bankDepositTransactionEntity.id.eq(accountTransactionEntity.transactionId)))
                .leftJoin(eventDepositTransactionEntity)
                .on(accountTransactionEntity.transactionSubType.eq(TransactionSubType.EVENT)
                        .and(eventDepositTransactionEntity.id.eq(accountTransactionEntity.transactionId)))
                .leftJoin(cardPaymentTransactionEntity)
                .on(accountTransactionEntity.transactionSubType.eq(TransactionSubType.CARD_PAYMENT)
                        .and(cardPaymentTransactionEntity.id.eq(accountTransactionEntity.transactionId)))
                .leftJoin(transferTransactionEntity)
                .on(accountTransactionEntity.transactionSubType.eq(TransactionSubType.TRANSFER)
                        .and(transferTransactionEntity.id.eq(accountTransactionEntity.transactionId)))
                .leftJoin(memberEntity)
                .on(
                        (accountTransactionEntity.transactionSubType.eq(TransactionSubType.MEMBERSHIP_FEE)
                                .and(memberShipFeeDepositTransactionEntity.depositorId.eq(memberEntity.id)))
                                .or(accountTransactionEntity.transactionSubType.eq(TransactionSubType.EVENT)
                                        .and(eventDepositTransactionEntity.applicantId.eq(memberEntity.id))
                                ))
                .leftJoin(eventEntity)
                .on(accountTransactionEntity.transactionSubType.eq(TransactionSubType.EVENT)
                        .and(eventEntity.id.eq(eventDepositTransactionEntity.eventId)));

        query
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize());

        return query.fetch();
    }
}