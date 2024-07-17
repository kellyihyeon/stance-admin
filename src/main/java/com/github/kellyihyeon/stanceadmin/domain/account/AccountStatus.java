package com.github.kellyihyeon.stanceadmin.domain.account;

public enum AccountStatus {
    ACTIVE("활성"),
    INACTIVE("비활성");


    private final String status;

    AccountStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
