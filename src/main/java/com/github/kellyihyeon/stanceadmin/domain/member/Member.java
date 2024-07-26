package com.github.kellyihyeon.stanceadmin.domain.member;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    private String invitationCode;

    private String email;

    private String password;

    private String name;

    private MemberRole memberRole;

    private UserPermissionLevel permissionLevel;

    private MemberType memberType;

    private RegistrationStatus registrationStatus;

    private LocalDateTime signUpDate;

    private LocalDateTime withdrawalDate;

    private Long updaterId;

    private LocalDateTime updatedDate;

    private Member(String invitationCode, String email, String password, String name, MemberRole memberRole, UserPermissionLevel permissionLevel, MemberType memberType, RegistrationStatus registrationStatus, LocalDateTime signUpDate) {
        this.invitationCode = invitationCode;
        this.email = email;
        this.password = password;
        this.name = name;
        this.memberRole = memberRole;
        this.permissionLevel = permissionLevel;
        this.memberType = memberType;
        this.registrationStatus = registrationStatus;
        this.signUpDate = signUpDate;
    }
}
