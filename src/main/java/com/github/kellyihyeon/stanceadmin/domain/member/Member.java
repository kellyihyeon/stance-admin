package com.github.kellyihyeon.stanceadmin.domain.member;

import com.github.kellyihyeon.stanceadmin.shared.exception.CustomException;
import com.github.kellyihyeon.stanceadmin.shared.exception.ErrorCode;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.Locale;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    private Long id;

    private String code;

    private String email;

    private String password;

    private String name;

    private String nickname;

    private Integer backNumber;

    private MemberRole  memberRole;

    private PermissionLevel permissionLevel;

    private MemberType memberType;

    private RegistrationStatus registrationStatus;

    private LocalDateTime signUpDate;

    private LocalDateTime withdrawalDate;

    private Long updaterId;

    private LocalDateTime updatedDate;

    public Member(Long id, String code, String email, String password, String name, String nickname, Integer backNumber, MemberRole memberRole, PermissionLevel permissionLevel, MemberType memberType, RegistrationStatus registrationStatus, LocalDateTime signUpDate, LocalDateTime withdrawalDate, Long updaterId, LocalDateTime updatedDate) {
        this.id = id;
        this.code = code;
        this.email = email;
        this.password = password;
        this.name = name;
        this.nickname = nickname;
        this.backNumber = backNumber;
        this.memberRole = memberRole;
        this.permissionLevel = permissionLevel;
        this.memberType = memberType;
        this.registrationStatus = registrationStatus;
        this.signUpDate = signUpDate;
        this.withdrawalDate = withdrawalDate;
        this.updaterId = updaterId;
        this.updatedDate = updatedDate;
    }

    public Member(String email, String password, String name, PasswordEncoder passwordEncoder) {
        validateRequiredFields(email, password, name);

        final int MEMBER_CODE_LENGTH = 8;
        this.code = RandomStringUtils.randomAlphanumeric(MEMBER_CODE_LENGTH).toUpperCase(Locale.ROOT);
        this.email = email;
        this.password = passwordEncoder.encode(password);
        this.name = name;
        this.memberRole = MemberRole.MEMBER;
        this.permissionLevel = PermissionLevel.USER;
        this.memberType = MemberType.ACTIVE;
        this.registrationStatus = RegistrationStatus.REGISTERED;
        this.signUpDate = LocalDateTime.now();
    }

    private void validateRequiredFields(String email, String password, String name) {
        if (email == null || password == null || name == null) {
            throw new CustomException(ErrorCode.MISSING_REQUIRED_FIELD);
        }
    }

    public void verifyPassword(String inputPassword, PasswordEncoder passwordEncoder) {
        if (!passwordEncoder.matches(inputPassword, this.password)) {
            throw new CustomException(ErrorCode.INVALID_PASSWORD);
        }
    }
}
