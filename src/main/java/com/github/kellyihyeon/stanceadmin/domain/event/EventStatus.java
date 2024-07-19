package com.github.kellyihyeon.stanceadmin.domain.event;

public enum EventStatus {
    ACTIVE("활성"),
    INACTIVE("비활성"),
    ;

    private final String status;

    EventStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

}
