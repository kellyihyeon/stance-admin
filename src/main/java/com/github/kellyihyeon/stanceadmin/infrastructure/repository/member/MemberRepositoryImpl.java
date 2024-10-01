package com.github.kellyihyeon.stanceadmin.infrastructure.repository.member;

import com.github.kellyihyeon.stanceadmin.application.member.MemberMapper;
import com.github.kellyihyeon.stanceadmin.domain.member.*;
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
    public List<Member> findParticipatingMembers(MemberRole memberRole, List<MemberStatus> memberStatuses, RegistrationStatus registrationStatus) {
        List<MemberEntity> entities = jpaRepository.findByMemberRoleAndMemberStatusInAndRegistrationStatus(memberRole, memberStatuses, registrationStatus);

        List<Member> result = new ArrayList<>();
        for (MemberEntity entity : entities) {
            result.add(mapper.toDomain(entity));
        }

        return result;
    }

    @Override
    public Member findSystemAdminKey(UserPermissionLevel permissionLevel) {
        MemberEntity systemAdmin = jpaRepository.findByPermissionLevel(permissionLevel);
        return mapper.toDomain(systemAdmin);
    }

}
