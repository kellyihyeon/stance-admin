package com.github.kellyihyeon.stanceadmin.application.member;

import com.github.kellyihyeon.stanceadmin.domain.member.Member;
import com.github.kellyihyeon.stanceadmin.infrastructure.entity.member.MemberEntity;

public interface MemberMapper {

    Member toDomain(MemberEntity entity);
}
