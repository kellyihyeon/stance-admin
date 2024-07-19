package com.github.kellyihyeon.stanceadmin.domain.eventapplicant;

import java.util.Objects;

public class EventApplicant {


    public EventApplicant(Long id, Long eventId, Long applicantId, String description, DepositStatus depositStatus) {

    }

    public static EventApplicant create(Long id, Long eventId, Long applicantId, String description, DepositStatus depositStatus) {
        Objects.requireNonNull(eventId, "eventId 는 null 이어서는 안됩니다.");
        Objects.requireNonNull(applicantId, "applicantId 는 null 이어서는 안됩니다.");

        return new EventApplicant(id, eventId, applicantId, description, depositStatus);

    }
}
