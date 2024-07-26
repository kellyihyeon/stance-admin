package com.github.kellyihyeon.stanceadmin.domain.member;

public enum MemberRole {

    MEMBER("회원"),
    COACH("코치");

    private String displayName;

    MemberRole(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
