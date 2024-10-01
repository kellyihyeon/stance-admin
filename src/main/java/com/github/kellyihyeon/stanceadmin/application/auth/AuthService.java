package com.github.kellyihyeon.stanceadmin.application.auth;

import com.github.kellyihyeon.stanceadmin.domain.member.Member;
import com.github.kellyihyeon.stanceadmin.domain.member.MemberRepository;
import com.github.kellyihyeon.stanceadmin.domain.member.UserPermissionLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final MemberRepository memberRepository;

    public void checkSystemAdminPermission(String key) {
        Member admin = memberRepository.findSystemAdminKey(UserPermissionLevel.SYSTEM_ADMIN);
        if (!admin.isSystemAdmin(key)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
    }
}
