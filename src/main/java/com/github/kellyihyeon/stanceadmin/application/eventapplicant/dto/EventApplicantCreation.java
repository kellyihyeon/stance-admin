package com.github.kellyihyeon.stanceadmin.application.eventapplicant.dto;

import com.github.kellyihyeon.stanceadmin.domain.eventapplicant.DepositStatus;

import java.util.List;

public record EventApplicantCreation (
        Long eventId,
        List<Long> applicantIds,
        String description,
        DepositStatus depositStatus
)
{
    public EventApplicantCreation(Long eventId, List<Long> applicantIds, String description, DepositStatus depositStatus) {
        this.eventId = eventId;
        this.applicantIds = applicantIds;
        this.description = description;
        this.depositStatus = depositStatus;
    }
}
