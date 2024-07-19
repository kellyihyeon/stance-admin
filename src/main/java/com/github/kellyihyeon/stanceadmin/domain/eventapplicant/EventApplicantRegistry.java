package com.github.kellyihyeon.stanceadmin.domain.eventapplicant;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EventApplicantRegistry {

    private Long id;

    private Long eventId;

    private Long applicantId;

    private String description;

    private DepositStatus depositStatus;


    private EventApplicantRegistry(Long id, Long eventId, Long applicantId, String description, DepositStatus depositStatus) {
        this.id = id;
        this.eventId = eventId;
        this.applicantId = applicantId;
        this.description = description;
        this.depositStatus = depositStatus;
    }

    public static EventApplicantRegistry create(Long id, Long eventId, Long applicantId, String description) {
        Objects.requireNonNull(eventId, "eventId 는 null 이어서는 안됩니다.");
        Objects.requireNonNull(applicantId, "applicantId 는 null 이어서는 안됩니다.");

        return new EventApplicantRegistry(id, eventId, applicantId, description, DepositStatus.NOT_COMPLETED);

    }
}