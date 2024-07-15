package com.github.kellyihyeon.stanceadmin.domain.account;

public enum Bank {
    NH("농협은행"),
    SH("신한은행"),
    KAKAO("카카오뱅크");

    private String displayName;

    Bank(String displayName) {
        this.displayName = displayName;
    }
}
