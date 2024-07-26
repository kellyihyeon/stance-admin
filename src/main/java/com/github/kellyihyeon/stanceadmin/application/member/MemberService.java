package com.github.kellyihyeon.stanceadmin.application.member;

import com.github.kellyihyeon.stanceadmin.application.member.dto.MemberSummaryResponse;
import com.github.kellyihyeon.stanceadmin.domain.member.Member;
import com.github.kellyihyeon.stanceadmin.domain.member.MemberRepository;
import com.github.kellyihyeon.stanceadmin.domain.member.MemberType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public List<MemberSummaryResponse> getParticipatingMembers() {
        List<Member> participatingMembers = memberRepository.findParticipatingMembers(List.of(MemberType.ACTIVE, MemberType.DORMANT));

        List<MemberSummaryResponse> result = new ArrayList<>();
        for (Member member : participatingMembers) {
            Long memberId = 1L;
            result.add(
                    new MemberSummaryResponse(memberId, member.getName(), member.getMemberType())
            );
        }

        return result;
    }

}
