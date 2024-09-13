package com.github.kellyihyeon.stanceadmin.domain.event;

import lombok.Getter;

@Getter
public enum EventItem {
    TEAM_PARTY("회식"),
    UNIFORM("유니폼"),
    MEMBERSHIP_TRAINING("엠티");

    private String displayName;

    EventItem(String displayName) {
        this.displayName = displayName;
    }

    public static String toDisplayName(String name) {
        for (EventItem eventItem : EventItem.values()) {
            if (eventItem.name().equalsIgnoreCase(name)) {
                return eventItem.getDisplayName();
            }
        }

        return null;
    }
}
