package com.github.kellyihyeon.stanceadmin.infrastructure.entity.member;

import com.github.kellyihyeon.stanceadmin.domain.member.*;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
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
    @Column(name = "member_status", nullable = false)
    private MemberStatus memberStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "member_type", nullable = false)
    private MemberType memberType;

    @Enumerated(EnumType.STRING)
    @Column(name = "registration_status", nullable = false)
    private RegistrationStatus registrationStatus;

    @Column(name = "joining_date", nullable = false)
    private LocalDate joiningDate;

    @Column(name = "sign_up_date", nullable = false)
    private LocalDateTime signUpDate;

    @Column(name = "withdrawal_date")
    private LocalDateTime withdrawalDate;

    @Column(name = "updater_id")
    private Long updaterId;

    @Column(name = "updated_date")
    private LocalDateTime updatedDate;

    private MemberEntity(Long id, String invitationCode, String email, String password, String name, MemberRole memberRole, UserPermissionLevel permissionLevel, MemberStatus memberStatus, RegistrationStatus registrationStatus, LocalDate joiningDate, LocalDateTime signUpDate) {
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
}
