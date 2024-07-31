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
    public EventApplicantRegistryEntity toEntity(EventApplicantRegistry domain) {
        return new EventApplicantRegistryEntity(
                domain.getEventId(),
                domain.getApplicantId(),
                domain.getDescription(),
                domain.getDepositStatus(),
                domain.getCreatedAt(),
                domain.getCreatorId()
        );
    }

    @Override
    public List<EventApplicantRegistry> toDomains(List<EventApplicantRegistryEntity> entities) {
        List<EventApplicantRegistry> result = new ArrayList<>();

        for (EventApplicantRegistryEntity entity : entities) {
            result.add(EventApplicantRegistry.createWithId(
                    entity.getId(),
                    entity.getEventId(),
                    entity.getApplicantId(),
                    entity.getDescription(),
                    entity.getCreatedAt(),
                    entity.getCreatorId()
            ));
        }

        return result;
    }

    @Override
    public List<EventApplicantRegistryEntity> toEntities(List<EventApplicantRegistry> domains) {
        List<EventApplicantRegistryEntity> result = new ArrayList<>();

        for (EventApplicantRegistry domain : domains) {
            result.add(EventApplicantRegistryEntity.createWithAllFields(
                    domain.getId(),
                    domain.getEventId(),
                    domain.getApplicantId(),
                    domain.getDescription(),
                    domain.getDepositStatus(),
                    domain.getCreatedAt(),
                    domain.getCreatorId(),
                    domain.getUpdatedAt(),
                    domain.getUpdaterId()
            ));
        }

        return result;
    }

}
