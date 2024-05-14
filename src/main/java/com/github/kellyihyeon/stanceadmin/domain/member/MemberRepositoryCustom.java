package com.github.kellyihyeon.stanceadmin.domain.member;

import java.util.List;
import java.util.Optional;

public interface MemberRepositoryCustom {

    Optional<Member> findMember(String email, List<RegistrationStatus> statuses);
}
