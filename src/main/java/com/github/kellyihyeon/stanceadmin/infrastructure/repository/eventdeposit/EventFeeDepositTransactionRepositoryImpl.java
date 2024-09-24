package com.github.kellyihyeon.stanceadmin.infrastructure.repository.eventdeposit;

import com.github.kellyihyeon.stanceadmin.application.eventapplicantregistry.dto.ApplicantRegistryData;
import com.github.kellyihyeon.stanceadmin.application.eventapplicantregistry.dto.QApplicantRegistryData;
import com.github.kellyihyeon.stanceadmin.application.eventdeposit.EventDepositTransactionMapper;
import com.github.kellyihyeon.stanceadmin.domain.eventapplicantregistry.EventApplicantDepositRegistry;
import com.github.kellyihyeon.stanceadmin.domain.eventdeposit.EventDepositTransaction;
import com.github.kellyihyeon.stanceadmin.domain.eventdeposit.EventFeeDepositTransactionRepository;
import com.github.kellyihyeon.stanceadmin.infrastructure.repository.eventapplicantregistry.EventApplicantDepositRegistryMapper;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.github.kellyihyeon.stanceadmin.infrastructure.entity.event.QEventEntity.eventEntity;
import static com.github.kellyihyeon.stanceadmin.infrastructure.entity.member.QMemberEntity.memberEntity;
import static com.github.kellyihyeon.stanceadmin.infrastructure.repository.eventapplicantregistry.QEventApplicantRegistryEntity.eventApplicantRegistryEntity;

@Repository
@RequiredArgsConstructor
public class EventFeeDepositTransactionRepositoryImpl implements EventFeeDepositTransactionRepository {

    private final JpaEventDepositTransactionEntityRepository jpaRepository;
    private final EventDepositTransactionMapper mapper;

    private final JPAQueryFactory jpaQueryFactory;
    private final EventApplicantDepositRegistryMapper depositRegistryMapper;

    @Override
    public Long saveEventDepositTransaction(EventDepositTransaction domain) {
        EventDepositTransactionEntity entity = mapper.toEntity(domain);
        return jpaRepository.save(entity).getId();
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
