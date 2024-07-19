package com.github.kellyihyeon.stanceadmin.infrastructure.repository.eventapplicant;

import com.github.kellyihyeon.stanceadmin.application.eventapplicant.EventApplicantMapper;
import com.github.kellyihyeon.stanceadmin.domain.eventapplicant.EventApplicantRegistry;
import com.github.kellyihyeon.stanceadmin.domain.eventapplicant.EventApplicantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class EventApplicantRepositoryImpl implements EventApplicantRepository {

    private final JpaEventApplicantEntityRepository jpaRepository;

    @Override
    public void createEventApplicant(EventApplicantRegistry eventApplicantRegistry) {
        EventApplicantRegistryEntity entity = EventApplicantMapper.toEntity(eventApplicantRegistry);
        jpaRepository.save(entity);
    }
}
