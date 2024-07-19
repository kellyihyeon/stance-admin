package com.github.kellyihyeon.stanceadmin.application.eventapplicant;

import com.github.kellyihyeon.stanceadmin.application.eventapplicant.dto.EventApplicantCreation;
import com.github.kellyihyeon.stanceadmin.domain.eventapplicant.EventApplicant;
import com.github.kellyihyeon.stanceadmin.infrastructure.repository.eventapplicant.EventApplicantEntity;

import java.util.ArrayList;
import java.util.List;

public class EventApplicantMapper {

    public static List<EventApplicant> toDomains(EventApplicantCreation eventApplicantCreation) {
        List<EventApplicant> eventApplicants = new ArrayList<>();

        for (Long applicantId : eventApplicantCreation.applicantIds()) {
            eventApplicants.add(EventApplicant.create(
                    null,
                    eventApplicantCreation.eventId(),
                    applicantId,
                    eventApplicantCreation.description(),
                    eventApplicantCreation.depositStatus()
            ));

        }
        return eventApplicants;
    }

    public static EventApplicantEntity toEntity(EventApplicant eventApplicant) {
        return null;
    }
}
