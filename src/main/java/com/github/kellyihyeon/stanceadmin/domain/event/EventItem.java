package com.github.kellyihyeon.stanceadmin.domain.event;

public enum EventItem {
    TEAM_PARTY("회식"),
    UNIFORM("유니폼"),
    MEMBERSHIP_TRAINING("엠티");

    private String displayName;

    EventItem(String displayName) {
        this.displayName = displayName;
    }
}
