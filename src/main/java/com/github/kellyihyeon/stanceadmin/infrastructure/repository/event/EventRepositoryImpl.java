package com.github.kellyihyeon.stanceadmin.infrastructure.repository.event;

import com.github.kellyihyeon.stanceadmin.application.event.EventMapper;
import com.github.kellyihyeon.stanceadmin.domain.event.Event;
import com.github.kellyihyeon.stanceadmin.domain.event.EventRepository;
import com.github.kellyihyeon.stanceadmin.domain.event.EventStatus;
import com.github.kellyihyeon.stanceadmin.infrastructure.entity.event.EventEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class EventRepositoryImpl implements EventRepository {

    private final JpaEventRepository jpaRepository;
    private final EventMapper mapper;

    @Override
    public void createEvent(Event event) {
        EventEntity entity = mapper.toEntity(event);
        jpaRepository.save(entity);
    }

    @Override
    public boolean existsByIdAndStatus(Long eventId, EventStatus status) {
        return jpaRepository.existsByIdAndStatus(eventId, status);
    }

    @Override
    public List<Event> getEventsByStatus(EventStatus eventStatus) {
        List<EventEntity> entities = jpaRepository.findByStatus(eventStatus);
        return mapper.toDomains(entities);
    }

}
