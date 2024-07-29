package com.github.kellyihyeon.stanceadmin.application.member;

import com.github.kellyihyeon.stanceadmin.domain.member.Member;
import com.github.kellyihyeon.stanceadmin.infrastructure.entity.member.MemberEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MemberMapperImpl implements MemberMapper {

    @Override
    public Member toDomain(MemberEntity entity) {
        return Member.createWithId(
                entity.getId(),
                entity.getInvitationCode(),
                entity.getEmail(),
                entity.getPassword(),
                entity.getName(),
                entity.getMemberRole(),
                entity.getPermissionLevel(),
                entity.getMemberStatus(),
                entity.getRegistrationStatus(),
                entity.getJoiningDate(),
                entity.getSignUpDate()
        );
    }

    @Override
    public List<Member> toDomains(List<MemberEntity> entities) {
        List<Member> result = new ArrayList<>();

        for (MemberEntity entity : entities) {
            result.add(
                    Member.createWithId(
                            entity.getId(),
                            entity.getInvitationCode(),
                            entity.getEmail(),
                            entity.getPassword(),
                            entity.getName(),
                            entity.getMemberRole(),
                            entity.getPermissionLevel(),
                            entity.getMemberStatus(),
                            entity.getRegistrationStatus(),
                            entity.getJoiningDate(),
                            entity.getSignUpDate()
                    ));
        }
        return result;
    }
}
