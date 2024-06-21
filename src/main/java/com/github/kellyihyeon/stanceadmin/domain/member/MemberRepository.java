package com.github.kellyihyeon.stanceadmin.domain.member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {

    Optional<Member> findMember(String email, List<RegistrationStatus> statuses);

    void save(Member member);

    Long countByMemberTypeIn(List<MemberType> memberTypes);

}
