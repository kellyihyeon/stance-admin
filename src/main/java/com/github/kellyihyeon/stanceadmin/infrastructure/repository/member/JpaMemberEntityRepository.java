package com.github.kellyihyeon.stanceadmin.infrastructure.repository.member;

import com.github.kellyihyeon.stanceadmin.domain.member.*;
import com.github.kellyihyeon.stanceadmin.infrastructure.entity.member.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaMemberEntityRepository extends JpaRepository<MemberEntity, Long> {

    List<MemberEntity> findByMemberRoleAndMemberStatusInAndRegistrationStatus(MemberRole memberRole, List<MemberStatus> memberStatuses, RegistrationStatus registrationStatus);

    MemberEntity findByPermissionLevel(UserPermissionLevel permissionLevel);

}
