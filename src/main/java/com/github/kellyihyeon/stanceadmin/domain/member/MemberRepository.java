package com.github.kellyihyeon.stanceadmin.domain.member;

import java.util.List;

public interface MemberRepository {

    List<Member> findParticipatingMembers(MemberRole memberRole, List<MemberStatus> memberStatuses);
}
