package com.github.kellyihyeon.stanceadmin.infrastructure.repository.membershipfeedeposit;

import com.github.kellyihyeon.stanceadmin.application.accounttransaction.MembershipFeeDepositTransactionMapper;
import com.github.kellyihyeon.stanceadmin.application.member.MemberMapper;
import com.github.kellyihyeon.stanceadmin.application.membershipfeedeposit.dto.DepositRegistryData;
import com.github.kellyihyeon.stanceadmin.application.membershipfeedeposit.dto.QDepositRegistryData;
import com.github.kellyihyeon.stanceadmin.domain.member.Member;
import com.github.kellyihyeon.stanceadmin.domain.member.MemberRole;
import com.github.kellyihyeon.stanceadmin.domain.member.RegistrationStatus;
import com.github.kellyihyeon.stanceadmin.domain.membershipfeedeposit.MembershipFeeDepositRegistry;
import com.github.kellyihyeon.stanceadmin.domain.membershipfeedeposit.MembershipFeeDepositRepository;
import com.github.kellyihyeon.stanceadmin.domain.membershipfeedeposit.MembershipFeeDepositTransaction;
import com.github.kellyihyeon.stanceadmin.infrastructure.entity.member.MemberEntity;
import com.github.kellyihyeon.stanceadmin.infrastructure.entity.membershipfeedeposit.MemberShipFeeDepositTransactionEntity;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static com.github.kellyihyeon.stanceadmin.infrastructure.entity.member.QMemberEntity.memberEntity;
import static com.github.kellyihyeon.stanceadmin.infrastructure.entity.membershipfeedeposit.QMemberShipFeeDepositTransactionEntity.memberShipFeeDepositTransactionEntity;

@Repository
@RequiredArgsConstructor
public class MembershipFeeDepositRepositoryImpl implements MembershipFeeDepositRepository {

    private final JpaMembershipFeeDepositTransactionEntityRepository jpaRepository;
    private final JPAQueryFactory jpaQueryFactory;

    private final MemberMapper memberMapper;
    private final MembershipFeeDepositTransactionMapper membershipFeeDepositTransactionMapper;


    @Override
    public Long createMembershipFeeDepositTransaction(MembershipFeeDepositTransaction domain) {
        MemberShipFeeDepositTransactionEntity entity = membershipFeeDepositTransactionMapper.toEntity(domain);
        return jpaRepository.save(entity).getId();
    }

    @Override
    public List<Member> findPaidMembers(LocalDate startDate, LocalDate endDate) {
        List<Tuple> tuples = jpaQueryFactory
                .select(memberEntity, memberShipFeeDepositTransactionEntity)
                .from(memberEntity)
                .leftJoin(memberShipFeeDepositTransactionEntity)
                .on(memberEntity.id.eq(memberShipFeeDepositTransactionEntity.depositorId)
                )
                .where(memberEntity.memberRole.eq(MemberRole.MEMBER)
                        .and(memberShipFeeDepositTransactionEntity.dueDate.between(startDate, endDate))
                        .and(memberEntity.registrationStatus.eq(RegistrationStatus.REGISTERED))
                )
                .fetch();

        List<MemberEntity> entities = tuples.stream()
                .map(tuple -> tuple.get(memberEntity))
                .collect(Collectors.toList());
        return memberMapper.toDomains(entities);
    }

    @Override
    public List<MembershipFeeDepositRegistry> getDepositRegistries(LocalDate startDate, LocalDate endDate) {
        List<DepositRegistryData> registryData = jpaQueryFactory
                .select(new QDepositRegistryData(
                        memberEntity.id,
                        memberEntity.name,
                        memberShipFeeDepositTransactionEntity.amount,
                        memberEntity.memberStatus,
                        memberShipFeeDepositTransactionEntity.dueDate,
                        memberShipFeeDepositTransactionEntity.depositDate
                ))
                .from(memberEntity)
                .leftJoin(memberShipFeeDepositTransactionEntity)
                .on(memberEntity.id.eq(memberShipFeeDepositTransactionEntity.depositorId)
                        .and(memberShipFeeDepositTransactionEntity.dueDate.between(startDate, endDate))
                )
                .where(memberEntity.memberRole.eq(MemberRole.MEMBER)
                        .and(memberEntity.registrationStatus.eq(RegistrationStatus.REGISTERED))
                )
                .fetch();

        return null;
    }
}
