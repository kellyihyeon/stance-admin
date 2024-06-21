package com.github.kellyihyeon.stanceadmin.infrastructure.repository.member;

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

    @Column(name = "code", nullable = false)
    private String code;

    @Column(name = "email")
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "back_number")
    private Integer backNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "member_role", nullable = false)
    private MemberRole memberRole;

    @Enumerated(EnumType.STRING)
    @Column(name = "permission_level", nullable = false)
    private PermissionLevel permissionLevel;

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

    public MemberEntity(Member member) {
        this.code = member.getCode();
        this.email = member.getEmail();
        this.password = member.getPassword();
        this.name = member.getName();
        this.memberRole = member.getMemberRole();
        this.memberType = member.getMemberType();
        this.permissionLevel = member.getPermissionLevel();
        this.registrationStatus = member.getRegistrationStatus();
        this.signUpDate = member.getSignUpDate();
    }

    public Member toDomain() {
        return new Member(
                this.id,
                this.code,
                this.email,
                this.password,
                this.name,
                this.nickname,
                this.backNumber,
                this.memberRole,
                this.permissionLevel,
                this.memberType,
                this.registrationStatus,
                this.signUpDate,
                this.withdrawalDate,
                this.updaterId,
                this.updatedDate
        );
    }

}
