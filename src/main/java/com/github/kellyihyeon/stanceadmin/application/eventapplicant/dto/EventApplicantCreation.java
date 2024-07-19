package com.github.kellyihyeon.stanceadmin.application.eventapplicant.dto;

import java.util.List;

public record EventApplicantCreation (
        Long eventId,
        List<Long> applicantIds,
        String description
)
{

    public EventApplicantCreation(Long eventId, List<Long> applicantIds, String description) {
        this.eventId = eventId;
        this.applicantIds = applicantIds;
        this.description = description;
    }
}
