package com.github.kellyihyeon.stanceadmin.infrastructure.repository.eventapplicantregistry;

import com.github.kellyihyeon.stanceadmin.infrastructure.querydsl.EventApplicantProjection;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.github.kellyihyeon.stanceadmin.infrastructure.entity.member.QMemberEntity.memberEntity;
import static com.github.kellyihyeon.stanceadmin.infrastructure.repository.eventapplicantregistry.QEventApplicantRegistryEntity.eventApplicantRegistryEntity;

@Repository
@RequiredArgsConstructor
public class EventApplicantQueryRepositoryImpl implements EventApplicantQueryRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<EventApplicantProjection> findApplicantsByEventId(Long eventId) {
        return queryFactory
                .select(
                        Projections.fields(
                                EventApplicantProjection.class,
                                memberEntity.id.as("memberId"),
                                memberEntity.name.as("memberName"),
                                eventApplicantRegistryEntity.description.as("memo"),
                                eventApplicantRegistryEntity.createdAt
                        )
                )
                .from(eventApplicantRegistryEntity)
                .join(memberEntity)
                .on(eventApplicantRegistryEntity.applicantId.eq(memberEntity.id))
                .where(eventApplicantRegistryEntity.eventId.eq(eventId))
                .orderBy(eventApplicantRegistryEntity.createdAt.desc())
                .fetch();
    }

}
