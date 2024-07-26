package com.github.kellyihyeon.stanceadmin.infrastructure.entity.member;

import com.github.kellyihyeon.stanceadmin.domain.member.*;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Table(name = "members")
public class MemberEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "invitation_code", nullable = false)
    private String invitationCode;

    @Column(name = "email")
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "name", nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "member_role", nullable = false)
    private MemberRole memberRole;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_permission_level", nullable = false)
    private UserPermissionLevel permissionLevel;

    @Enumerated(EnumType.STRING)
    @Column(name = "member_type", nullable = false)
    private MemberType memberType;

    @Enumerated(EnumType.STRING)
    @Column(name = "registration_status", nullable = false)
    private RegistrationStatus registrationStatus;

    @Column(name = "sign_up_date", nullable = false)
    private LocalDateTime signUpDate;

    @Column(name = "withdrawal_date")
    private LocalDateTime withdrawalDate;

    @Column(name = "updater_id")
    private Long updaterId;

    @Column(name = "updated_date")
    private LocalDateTime updatedDate;

    private MemberEntity(String invitationCode, String email, String password, String name, MemberRole memberRole, UserPermissionLevel permissionLevel, MemberType memberType, RegistrationStatus registrationStatus, LocalDateTime signUpDate) {
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
