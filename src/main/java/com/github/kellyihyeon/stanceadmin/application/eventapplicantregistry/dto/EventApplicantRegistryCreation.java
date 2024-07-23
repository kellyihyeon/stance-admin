package com.github.kellyihyeon.stanceadmin.application.eventapplicantregistry.dto;

import java.util.List;

public record EventApplicantRegistryCreation(
        Long eventId,
        List<Long> applicantIds,
        String description
)
{

    public EventApplicantRegistryCreation(Long eventId, List<Long> applicantIds, String description) {
        this.eventId = eventId;
        this.applicantIds = applicantIds;
        this.description = description;
    }
}
