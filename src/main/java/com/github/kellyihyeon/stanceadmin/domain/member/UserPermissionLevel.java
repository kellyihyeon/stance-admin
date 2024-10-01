package com.github.kellyihyeon.stanceadmin.domain.member;

public enum UserPermissionLevel {

    SYSTEM_ADMIN("시스템 관리자"),
    ADMIN("관리자"),
    USER("일반 사용자"),
    RESTRICTED("제한된 접근 권한 사용자");

    private String displayName;

    UserPermissionLevel(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
