package com.github.kellyihyeon.stanceadmin.application.eventapplicantregistry;

import com.github.kellyihyeon.stanceadmin.application.eventapplicantregistry.dto.EventApplicantRegistryCreation;
import com.github.kellyihyeon.stanceadmin.domain.eventapplicantregistry.EventApplicantRegistry;
import com.github.kellyihyeon.stanceadmin.infrastructure.repository.eventapplicantregistry.EventApplicantRegistryEntity;

import java.util.List;

public interface EventApplicantRegistryMapper {

    List<EventApplicantRegistry> toDomains(EventApplicantRegistryCreation eventApplicantRegistryCreation);

    EventApplicantRegistryEntity toEntity(EventApplicantRegistry domain);

    List<EventApplicantRegistry> toDomains(List<EventApplicantRegistryEntity> entities);

}
