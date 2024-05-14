package com.github.kellyihyeon.stanceadmin.infrastructure.repository.member;

import com.github.kellyihyeon.stanceadmin.domain.member.Member;
import com.github.kellyihyeon.stanceadmin.domain.member.MemberRepositoryCustom;
import com.github.kellyihyeon.stanceadmin.domain.member.RegistrationStatus;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.github.kellyihyeon.stanceadmin.domain.member.QMember.member;

@Repository
@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<Member> findMember(String email, List<RegistrationStatus> statuses) {
        return Optional.ofNullable(
                queryFactory
                        .selectFrom(member)
                        .where(
                                member.email.eq(email),
                                member.registrationStatus.in(statuses)
                        )
                        .fetchOne());
    }

}
