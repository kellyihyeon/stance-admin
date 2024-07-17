package com.github.kellyihyeon.stanceadmin.domain.account;

public enum Bank {
    KB("국민은행"),
    HN("하나은행"),
    WR("우리은행"),
    IBK("기업은행"),
    NH("농협은행"),
    SH("신한은행"),
    KAKAO("카카오뱅크"),
    TOSS("토스뱅크");

    private String displayName;

    Bank(String displayName) {
        this.displayName = displayName;
    }
}
