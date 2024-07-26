package com.github.kellyihyeon.stanceadmin.domain.member;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    private Long id;

    private String invitationCode;

    private String email;

    private String password;

    private String name;

    private MemberRole memberRole;

    private UserPermissionLevel permissionLevel;

    private MemberStatus memberStatus;

    private MemberType memberType;

    private RegistrationStatus registrationStatus;

    private LocalDate joiningDate;

    private LocalDateTime signUpDate;

    private LocalDateTime withdrawalDate;

    private Long updaterId;

    private LocalDateTime updatedDate;

    private Member(Long id, String invitationCode, String email, String password, String name, MemberRole memberRole, UserPermissionLevel permissionLevel, MemberStatus memberStatus, RegistrationStatus registrationStatus, LocalDate joiningDate, LocalDateTime signUpDate) {
        this.id = id;
        this.invitationCode = invitationCode;
        this.email = email;
        this.password = password;
        this.name = name;
        this.memberRole = memberRole;
        this.permissionLevel = permissionLevel;
        this.memberStatus = memberStatus;
        this.registrationStatus = registrationStatus;
        this.joiningDate = joiningDate;
        this.signUpDate = signUpDate;
    }

    public static Member createWithId(Long id, String invitationCode, String email, String password, String name, MemberRole memberRole, UserPermissionLevel permissionLevel, MemberStatus memberStatus, RegistrationStatus registrationStatus, LocalDate joiningDate, LocalDateTime signUpDate) {
        return new Member(id, invitationCode, email, password, name, memberRole, permissionLevel, memberStatus, registrationStatus, joiningDate, signUpDate);
    }
}
