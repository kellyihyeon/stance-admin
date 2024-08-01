package com.github.kellyihyeon.stanceadmin.infrastructure.repository.eventapplicantregistry;

import com.github.kellyihyeon.stanceadmin.application.eventapplicantregistry.EventApplicantRegistryMapper;
import com.github.kellyihyeon.stanceadmin.application.eventapplicantregistry.dto.ApplicantRegistryData;
import com.github.kellyihyeon.stanceadmin.application.eventapplicantregistry.dto.QApplicantRegistryData;
import com.github.kellyihyeon.stanceadmin.domain.eventapplicantregistry.EventApplicantDepositRegistry;
import com.github.kellyihyeon.stanceadmin.domain.eventapplicantregistry.EventApplicantRegistry;
import com.github.kellyihyeon.stanceadmin.domain.eventapplicantregistry.EventApplicantRegistryRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.github.kellyihyeon.stanceadmin.infrastructure.entity.member.QMemberEntity.memberEntity;
import static com.github.kellyihyeon.stanceadmin.infrastructure.repository.event.QEventEntity.eventEntity;
import static com.github.kellyihyeon.stanceadmin.infrastructure.repository.eventapplicantregistry.QEventApplicantRegistryEntity.eventApplicantRegistryEntity;

@Repository
@RequiredArgsConstructor
public class EventApplicantRegistryRepositoryImpl implements EventApplicantRegistryRepository {

    private final JpaEventApplicantRegistryEntityRepository jpaRepository;
    private final EventApplicantRegistryMapper mapper;

    private final JPAQueryFactory jpaQueryFactory;
    private final EventApplicantDepositRegistryMapper depositRegistryMapper;


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
    public void updateAll(List<EventApplicantRegistry> domains) {
        List<EventApplicantRegistryEntity> entities = mapper.toEntities(domains);
        jpaRepository.saveAll(entities);
    }

    @Override
    public List<EventApplicantDepositRegistry> getEventApplicantRegistriesByEventId(Long eventId) {
        List<ApplicantRegistryData> applicantRegistryDataDto = jpaQueryFactory
                .select(new QApplicantRegistryData(
                        eventEntity.description,
                        eventEntity.dueDate,
                        memberEntity.name,
                        eventEntity.amount,
                        eventApplicantRegistryEntity.depositStatus
                ))
                .from(eventApplicantRegistryEntity)
                .leftJoin(eventEntity)
                    .on(eventApplicantRegistryEntity.eventId.eq(eventEntity.id))
                .leftJoin(memberEntity)
                    .on(eventApplicantRegistryEntity.applicantId.eq(memberEntity.id))
                .where(eventApplicantRegistryEntity.eventId.eq(eventId))
                .fetch();

        return depositRegistryMapper.toDomains(applicantRegistryDataDto);
    }

}
