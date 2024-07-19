package com.github.kellyihyeon.stanceadmin.application.eventapplicantregistry;

import com.github.kellyihyeon.stanceadmin.application.eventapplicantregistry.dto.EventApplicantRegistryCreation;
import com.github.kellyihyeon.stanceadmin.domain.eventapplicantregistry.EventApplicantRegistry;
import com.github.kellyihyeon.stanceadmin.infrastructure.repository.eventapplicantregistry.EventApplicantRegistryEntity;

import java.util.ArrayList;
import java.util.List;

public class EventApplicantRegistryMapper {

    public static List<EventApplicantRegistry> toDomains(EventApplicantRegistryCreation eventApplicantRegistryCreation) {
        List<EventApplicantRegistry> eventApplicantRegistries = new ArrayList<>();

        for (Long applicantId : eventApplicantRegistryCreation.applicantIds()) {
            eventApplicantRegistries.add(EventApplicantRegistry.create(
                    null,
                    eventApplicantRegistryCreation.eventId(),
                    applicantId,
                    eventApplicantRegistryCreation.description()
            ));

        }
        return eventApplicantRegistries;
    }

    public static EventApplicantRegistryEntity toEntity(EventApplicantRegistry eventApplicantRegistry) {
        return new EventApplicantRegistryEntity(
                eventApplicantRegistry.getEventId(),
                eventApplicantRegistry.getApplicantId(),
                eventApplicantRegistry.getDescription(),
                eventApplicantRegistry.getDepositStatus()
        );
    }
}
