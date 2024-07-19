package com.github.kellyihyeon.stanceadmin.infrastructure.repository.eventapplicantregistry;

import com.github.kellyihyeon.stanceadmin.application.eventapplicantregistry.EventApplicantRegistryMapper;
import com.github.kellyihyeon.stanceadmin.domain.eventapplicantregistry.EventApplicantRegistry;
import com.github.kellyihyeon.stanceadmin.domain.eventapplicantregistry.EventApplicantRegistryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class EventApplicantRegistryRepositoryImpl implements EventApplicantRegistryRepository {

    private final JpaEventApplicantRegistryEntityRepository jpaRepository;

    @Override
    public void createEventApplicant(EventApplicantRegistry eventApplicantRegistry) {
        EventApplicantRegistryEntity entity = EventApplicantRegistryMapper.toEntity(eventApplicantRegistry);
        jpaRepository.save(entity);
    }
}
