package com.github.kellyihyeon.stanceadmin.infrastructure.repository.eventapplicantregistry;

import com.github.kellyihyeon.stanceadmin.application.eventapplicantregistry.EventApplicantRegistryMapper;
import com.github.kellyihyeon.stanceadmin.domain.eventapplicantregistry.EventApplicantRegistry;
import com.github.kellyihyeon.stanceadmin.domain.eventapplicantregistry.EventApplicantRegistryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class EventApplicantRegistryRepositoryImpl implements EventApplicantRegistryRepository {

    private final JpaEventApplicantRegistryEntityRepository jpaRepository;
    private final EventApplicantRegistryMapper mapper;

    @Override
    public void saveEventApplicant(EventApplicantRegistry eventApplicantRegistry) {
        EventApplicantRegistryEntity entity = mapper.toEntity(eventApplicantRegistry);
        jpaRepository.save(entity);
    }

    @Override
    public List<EventApplicantRegistry> getRegistriesByEventIdAndDepositorIds(Long eventId, List<Long> depositorIds) {
        List<EventApplicantRegistryEntity> entities = jpaRepository.findByEventIdAndApplicantIdIn(eventId, depositorIds);

        return mapper.toDomains(entities);
    }

    @Override
    public void updateAll(List<EventApplicantRegistry> eventApplicantRegistries) {

    }

}
