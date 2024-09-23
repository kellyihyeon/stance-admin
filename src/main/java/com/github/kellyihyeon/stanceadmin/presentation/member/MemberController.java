package com.github.kellyihyeon.stanceadmin.presentation.member;

import com.github.kellyihyeon.stanceadmin.apis.MemberApi;
import com.github.kellyihyeon.stanceadmin.application.member.MemberService;
import com.github.kellyihyeon.stanceadmin.models.CurrentMemberResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemberController implements MemberApi {

    private final MemberService service;

    @Override
    public ResponseEntity<List<CurrentMemberResponse>> getCurrentMembers() {
        return ResponseEntity.ok(service.getCurrentMembers());
    }
}
