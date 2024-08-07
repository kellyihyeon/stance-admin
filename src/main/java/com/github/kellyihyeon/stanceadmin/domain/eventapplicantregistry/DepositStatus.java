package com.github.kellyihyeon.stanceadmin.domain.eventapplicantregistry;

import lombok.Getter;

@Getter
public enum DepositStatus {
    COMPLETED("입금 완료"),
    NOT_COMPLETED("미입금")
    ;

    private String displayName;

    DepositStatus(String displayName) {
        this.displayName = displayName;
    }
}
