package com.github.kellyihyeon.stanceadmin.application.member;

import com.github.kellyihyeon.stanceadmin.application.member.dto.MemberSummaryResponse;
import com.github.kellyihyeon.stanceadmin.domain.member.*;
import com.github.kellyihyeon.stanceadmin.models.CurrentMemberResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public List<MemberSummaryResponse> getParticipatingMembers() {
        List<Member> participatingMembers = memberRepository.findParticipatingMembers(
                MemberRole.MEMBER,
                List.of(MemberStatus.ACTIVE, MemberStatus.DORMANT),
                RegistrationStatus.REGISTERED
        );

        List<MemberSummaryResponse> result = new ArrayList<>();
        for (Member member : participatingMembers) {
            result.add(
                    new MemberSummaryResponse(member.getId(), member.getName(), member.getMemberStatus())
            );
        }

        return result;
    }

    public List<CurrentMemberResponse> getCurrentMembers() {
        List<MemberSummaryResponse> members = this.getParticipatingMembers();

        List<CurrentMemberResponse> currentMembers = new ArrayList<>();
        members
                .forEach(member ->{
                    currentMembers.add(new CurrentMemberResponse(member.id(), member.name()));
                });

        return currentMembers;
    }
}
