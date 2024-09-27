package com.github.kellyihyeon.stanceadmin.infrastructure.repository.event;

import com.github.kellyihyeon.stanceadmin.application.event.EventMapper;
import com.github.kellyihyeon.stanceadmin.domain.event.Event;
import com.github.kellyihyeon.stanceadmin.domain.event.EventStatus;
import com.github.kellyihyeon.stanceadmin.infrastructure.entity.event.EventEntity;
import com.github.kellyihyeon.stanceadmin.infrastructure.querydsl.EventProjection;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.github.kellyihyeon.stanceadmin.infrastructure.entity.event.QEventEntity.eventEntity;
import static com.github.kellyihyeon.stanceadmin.infrastructure.entity.member.QMemberEntity.memberEntity;

@Repository
@RequiredArgsConstructor
public class EventQueryRepositoryImpl implements EventQueryRepository {

    private final JpaEventRepository jpaRepository;
    private final EventMapper mapper;

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Event> getEventsByStatus(EventStatus eventStatus) {
        List<EventEntity> entities = jpaRepository.findByStatusOrderByDueDate(eventStatus);
        return mapper.toDomains(entities);
    }

    @Override
    public List<EventProjection> getEvents(PageRequest pageable) {
        JPAQuery<EventProjection> baseQuery = buildBaseQuery();
        JPAQuery<EventProjection> queryWithPaging = buildPagingQuery(baseQuery, pageable);
        return queryWithPaging.fetch();
    }

    @Override
    public long countTotalElements() {
        JPAQuery<EventProjection> baseQuery = buildBaseQuery();
        return baseQuery.fetch().size();
    }

    private JPAQuery<EventProjection> buildPagingQuery(JPAQuery<EventProjection> baseQuery, PageRequest pageable) {
        return baseQuery
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize());
    }

    private JPAQuery<EventProjection> buildBaseQuery() {
        return queryFactory
                .select(
                        Projections.constructor(
                                EventProjection.class,
                                eventEntity.id,
                                eventEntity.eventItem,
                                eventEntity.description.as("eventMemo"),
                                eventEntity.amount,
                                eventEntity.dueDate,
                                eventEntity.status,
                                eventEntity.createdAt,
                                memberEntity.name.as("creatorName")
                        ))
                .from(eventEntity)
                .leftJoin(memberEntity)
                .on(eventEntity.creatorId.eq(memberEntity.id))
                .orderBy(eventEntity.createdAt.desc());
    }

}
