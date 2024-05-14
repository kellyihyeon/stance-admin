package com.github.kellyihyeon.stanceadmin.domain.member;

import com.github.kellyihyeon.stanceadmin.application.auth.dto.SignUpForm;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.Locale;

@Entity
@Getter
@Table(name = "members")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

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
    private MemberRole  memberRole;

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

    public Member(SignUpForm signUpForm, PasswordEncoder passwordEncoder) {
        final int MEMBER_CODE_LENGTH = 8;
        this.code = RandomStringUtils.randomAlphanumeric(MEMBER_CODE_LENGTH).toUpperCase(Locale.ROOT);
        this.email = signUpForm.email();
        this.password = passwordEncoder.encode(signUpForm.password());
        this.name = signUpForm.name();
        this.memberRole = MemberRole.MEMBER;
        this.permissionLevel = PermissionLevel.USER;
        this.memberType = MemberType.ACTIVE;
        this.registrationStatus = RegistrationStatus.REGISTERED;
        this.signUpDate = LocalDateTime.now();
    }
}
