package com.github.kellyihyeon.stanceadmin.application.auth;

import com.github.kellyihyeon.stanceadmin.application.auth.dto.LoginForm;
import com.github.kellyihyeon.stanceadmin.domain.member.Member;
import com.github.kellyihyeon.stanceadmin.domain.member.RegistrationStatus;
import com.github.kellyihyeon.stanceadmin.infrastructure.repository.member.MemberRepository;
import com.github.kellyihyeon.stanceadmin.shared.exception.CustomException;
import com.github.kellyihyeon.stanceadmin.shared.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collections;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void signUp(String email, String password, String name) {
        checkDuplicatedUser(email);
        Member member = new Member(email, password, name, passwordEncoder);
        memberRepository.save(member);
    }

    private void checkDuplicatedUser(String email) {
        memberRepository.findMember(
                email,
                Arrays.asList(RegistrationStatus.REGISTERED, RegistrationStatus.LIMITED)
        ).ifPresent(
                member -> {
                    throw new CustomException(ErrorCode.DUPLICATE_MEMBER);
                }
        );
    }

    @Transactional
    public void login(LoginForm loginForm) {
        Member member = memberRepository.findMember(
                loginForm.getEmail(),
                Collections.singletonList(RegistrationStatus.REGISTERED)
        ).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_MEMBER));

        member.verifyPassword(loginForm.getPassword(), passwordEncoder);
    }
}
