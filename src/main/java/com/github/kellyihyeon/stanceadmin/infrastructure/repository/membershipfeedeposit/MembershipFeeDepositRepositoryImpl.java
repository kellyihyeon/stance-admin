package com.github.kellyihyeon.stanceadmin.infrastructure.repository.membershipfeedeposit;

import com.github.kellyihyeon.stanceadmin.application.member.MemberMapper;
import com.github.kellyihyeon.stanceadmin.domain.member.Member;
import com.github.kellyihyeon.stanceadmin.domain.member.MemberRole;
import com.github.kellyihyeon.stanceadmin.domain.member.RegistrationStatus;
import com.github.kellyihyeon.stanceadmin.domain.membershipfeedeposit.MembershipFeeDepositRepository;
import com.github.kellyihyeon.stanceadmin.infrastructure.entity.member.MemberEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

import static com.github.kellyihyeon.stanceadmin.infrastructure.entity.member.QMemberEntity.memberEntity;
import static com.github.kellyihyeon.stanceadmin.infrastructure.entity.membershipfeedeposit.QMemberShipFeeDepositTransactionEntity.memberShipFeeDepositTransactionEntity;

@Repository
@RequiredArgsConstructor
public class MembershipFeeDepositRepositoryImpl implements MembershipFeeDepositRepository {

    private final JPAQueryFactory jpaQueryFactory;

    private final MemberMapper memberMapper;


    @Override
    public List<Member> findPaidMembers(LocalDate startDate, LocalDate endDate) {
        List<MemberEntity> entities = jpaQueryFactory
                .select(memberEntity)
                .from(memberEntity)
                .leftJoin(memberShipFeeDepositTransactionEntity)
                .on(memberEntity.id.eq(memberShipFeeDepositTransactionEntity.depositorId)
                )
                .where(memberEntity.memberRole.eq(MemberRole.MEMBER)
                        .and(memberShipFeeDepositTransactionEntity.dueDate.between(startDate, endDate))
                        .and(memberEntity.registrationStatus.eq(RegistrationStatus.REGISTERED))
                )
                .fetch();

        return memberMapper.toDomains(entities);
    }

    @Override
    public List<Member> getDepositRoster(LocalDate startDate, LocalDate endDate) {
        List<MemberEntity> entities = jpaQueryFactory
                .select(memberEntity)
                .from(memberEntity)
                .leftJoin(memberShipFeeDepositTransactionEntity)
                .on(memberEntity.id.eq(memberShipFeeDepositTransactionEntity.depositorId)
                        .and(memberShipFeeDepositTransactionEntity.dueDate.between(startDate, endDate))
                )
                .where(memberEntity.memberRole.eq(MemberRole.MEMBER)
                        .and(memberEntity.registrationStatus.eq(RegistrationStatus.REGISTERED))
                )
                .fetch();

        return memberMapper.toDomains(entities);
    }
}
