package com.github.kellyihyeon.stanceadmin.infrastructure.repository.member;

import com.github.kellyihyeon.stanceadmin.domain.member.Member;
import com.github.kellyihyeon.stanceadmin.domain.member.MemberRepository;
import com.github.kellyihyeon.stanceadmin.domain.member.MemberType;
import com.github.kellyihyeon.stanceadmin.infrastructure.entity.member.MemberEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepository {

    private final JpaMemberEntityRepository jpaRepository;


    @Override
    public List<Member> findParticipatingMembers(List<MemberType> memberTypes) {
        List<MemberEntity> entities = jpaRepository.findByMemberTypeIn(memberTypes);

        return null;
    }
}
