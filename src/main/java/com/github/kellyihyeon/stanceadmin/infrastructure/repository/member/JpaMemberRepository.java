package com.github.kellyihyeon.stanceadmin.infrastructure.repository.member;

import com.github.kellyihyeon.stanceadmin.domain.member.MemberType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaMemberRepository extends JpaRepository<MemberEntity, Long> {

    Long countByMemberTypeIn(List<MemberType> memberTypes);
}
