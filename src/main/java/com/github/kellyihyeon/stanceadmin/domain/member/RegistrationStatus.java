package com.github.kellyihyeon.stanceadmin.domain.member;

public enum RegistrationStatus {
    REGISTERED("등록 회원"),
    WITHDRAWN("탈퇴 회원"),
    BLACKLISTED("블랙리스트 회원");

    private String displayName;

    RegistrationStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
