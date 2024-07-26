package com.github.kellyihyeon.stanceadmin.infrastructure.repository.member;

import com.github.kellyihyeon.stanceadmin.domain.member.MemberType;
import com.github.kellyihyeon.stanceadmin.infrastructure.entity.member.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaMemberEntityRepository extends JpaRepository<MemberEntity, Long> {

    List<MemberEntity> findByMemberTypeIn(List<MemberType> memberTypes);
}
