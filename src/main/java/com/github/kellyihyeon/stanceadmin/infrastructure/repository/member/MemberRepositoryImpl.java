package com.github.kellyihyeon.stanceadmin.infrastructure.repository.member;

import com.github.kellyihyeon.stanceadmin.domain.member.*;
import com.github.kellyihyeon.stanceadmin.infrastructure.entity.member.MemberEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepository {

    private final JpaMemberEntityRepository jpaRepository;

    @Override
    public List<Member> findParticipatingMembers(MemberRole memberRole, List<MemberStatus> memberStatuses) {
        List<MemberEntity> entities = jpaRepository.findByMemberRoleAndMemberStatusIn(memberRole, memberStatuses);

        return null;
    }
}
