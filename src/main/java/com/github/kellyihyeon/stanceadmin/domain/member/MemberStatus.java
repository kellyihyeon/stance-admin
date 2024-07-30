package com.github.kellyihyeon.stanceadmin.domain.member;

import lombok.Getter;

@Getter
public enum MemberStatus {

    ACTIVE("정기 회원"),
    DORMANT("휴면 회원"),
    GUEST("게스트"),
    COACH("코치")
    ;

    private String displayName;

    MemberStatus(String displayName) {
        this.displayName = displayName;
    }
}
