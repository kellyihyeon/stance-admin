package com.github.kellyihyeon.stanceadmin.application.eventapplicant;

import com.github.kellyihyeon.stanceadmin.application.eventapplicant.dto.EventApplicantCreation;
import com.github.kellyihyeon.stanceadmin.domain.eventapplicant.EventApplicantRegistry;
import com.github.kellyihyeon.stanceadmin.infrastructure.repository.eventapplicant.EventApplicantEntity;

import java.util.ArrayList;
import java.util.List;

public class EventApplicantMapper {

    public static List<EventApplicantRegistry> toDomains(EventApplicantCreation eventApplicantCreation) {
        List<EventApplicantRegistry> eventApplicantRegistries = new ArrayList<>();

        for (Long applicantId : eventApplicantCreation.applicantIds()) {
            eventApplicantRegistries.add(EventApplicantRegistry.create(
                    null,
                    eventApplicantCreation.eventId(),
                    applicantId,
                    eventApplicantCreation.description()
            ));

        }
        return eventApplicantRegistries;
    }

    public static EventApplicantEntity toEntity(EventApplicantRegistry eventApplicantRegistry) {
        return null;
    }
}
