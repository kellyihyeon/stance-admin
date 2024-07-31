package com.github.kellyihyeon.stanceadmin.application.eventapplicantregistry;

import com.github.kellyihyeon.stanceadmin.application.eventapplicantregistry.dto.EventApplicantRegistryCreation;
import com.github.kellyihyeon.stanceadmin.domain.eventapplicantregistry.EventApplicantRegistry;
import com.github.kellyihyeon.stanceadmin.infrastructure.repository.eventapplicantregistry.EventApplicantRegistryEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EventApplicantRegistryMapperImpl implements EventApplicantRegistryMapper {

    @Override
    public List<EventApplicantRegistry> toDomains(EventApplicantRegistryCreation serviceDto) {
        List<EventApplicantRegistry> result = new ArrayList<>();

        for (Long applicantId : serviceDto.applicantIds()) {
            result.add(EventApplicantRegistry.create(
                    serviceDto.eventId(),
                    applicantId,
                    serviceDto.description()
            ));
        }

        return result;
    }

    @Override
    public EventApplicantRegistryEntity toEntity(EventApplicantRegistry eventApplicantRegistry) {
        return new EventApplicantRegistryEntity(
                eventApplicantRegistry.getEventId(),
                eventApplicantRegistry.getApplicantId(),
                eventApplicantRegistry.getDescription(),
                eventApplicantRegistry.getDepositStatus(),
                eventApplicantRegistry.getCreatedAt(),
                eventApplicantRegistry.getCreatorId()
        );
    }

}
