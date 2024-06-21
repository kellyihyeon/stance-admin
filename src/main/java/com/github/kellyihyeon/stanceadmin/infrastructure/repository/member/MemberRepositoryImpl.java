package com.github.kellyihyeon.stanceadmin.infrastructure.repository.member;

import com.github.kellyihyeon.stanceadmin.domain.member.Member;
import com.github.kellyihyeon.stanceadmin.domain.member.MemberRepository;
import com.github.kellyihyeon.stanceadmin.domain.member.MemberType;
import com.github.kellyihyeon.stanceadmin.domain.member.RegistrationStatus;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.github.kellyihyeon.stanceadmin.infrastructure.repository.member.QMemberEntity.memberEntity;

@Repository
@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepository {

    private final JPAQueryFactory queryFactory;
    private final JpaMemberRepository jpaMemberRepository;

    @Override
    public Optional<Member> findMember(String email, List<RegistrationStatus> statuses) {
        return Optional.ofNullable(
                queryFactory
                        .selectFrom(memberEntity)
                        .where(
                                memberEntity.email.eq(email),
                                memberEntity.registrationStatus.in(statuses)
                        )
                        .fetchOne())
                .map(MemberEntity::toDomain);
    }

    @Override
    public void save(Member member) {
        MemberEntity memberEntity = new MemberEntity(member);
        jpaMemberRepository.save(memberEntity);
    }

    @Override
    public Long countByMemberTypeIn(List<MemberType> memberTypes) {
        return jpaMemberRepository.countByMemberTypeIn(memberTypes);
    }

}
