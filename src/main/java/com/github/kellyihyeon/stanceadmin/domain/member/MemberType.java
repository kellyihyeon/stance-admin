package com.github.kellyihyeon.stanceadmin.domain.member;

public enum MemberType {
    ACTIVE("정기 회원"),
    DORMANT("휴면 회원"),
    GUEST("게스트");

    private String displayName;

    MemberType(String displayName) {
        this.displayName = displayName;
    }
}
