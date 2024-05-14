package com.github.kellyihyeon.stanceadmin.infrastructure.repository.member;

import com.github.kellyihyeon.stanceadmin.domain.member.Member;
import com.github.kellyihyeon.stanceadmin.domain.member.MemberRepositoryCustom;
import com.github.kellyihyeon.stanceadmin.domain.member.MemberType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long>, MemberRepositoryCustom {

    Long countByMemberTypeIn(List<MemberType> memberTypes);
}
