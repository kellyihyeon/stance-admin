package com.github.kellyihyeon.stanceadmin.infrastructure.repository.member;

import com.github.kellyihyeon.stanceadmin.application.member.MemberMapper;
import com.github.kellyihyeon.stanceadmin.domain.member.Member;
import com.github.kellyihyeon.stanceadmin.domain.member.MemberRepository;
import com.github.kellyihyeon.stanceadmin.domain.member.MemberRole;
import com.github.kellyihyeon.stanceadmin.domain.member.MemberStatus;
import com.github.kellyihyeon.stanceadmin.infrastructure.entity.member.MemberEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepository {

    private final JpaMemberEntityRepository jpaRepository;
    private final MemberMapper mapper;

    @Override
    public List<Member> findParticipatingMembers(MemberRole memberRole, List<MemberStatus> memberStatuses) {
        List<MemberEntity> entities = jpaRepository.findByMemberRoleAndMemberStatusIn(memberRole, memberStatuses);

        List<Member> result = new ArrayList<>();
        for (MemberEntity entity : entities) {
            result.add(mapper.toDomain(entity));
        }

        return result;
    }
}
