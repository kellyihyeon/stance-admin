package com.github.kellyihyeon.stanceadmin.infrastructure.repository.event;

import com.github.kellyihyeon.stanceadmin.application.event.EventMapper;
import com.github.kellyihyeon.stanceadmin.domain.event.Event;
import com.github.kellyihyeon.stanceadmin.domain.event.EventRepository;
import com.github.kellyihyeon.stanceadmin.domain.event.EventStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class EventRepositoryImpl implements EventRepository {

    private final JpaEventRepository jpaRepository;

    @Override
    public void createEvent(Event event) {
        EventEntity entity = EventMapper.toEntity(event);
        jpaRepository.save(entity);
    }

    @Override
    public boolean existsByIdAndStatus(Long eventId, EventStatus status) {
        return jpaRepository.existsByIdAndStatus(eventId, status);
    }

    @Override
    public List<Event> getEventsByStatus(EventStatus eventStatus) {
        List<EventEntity> result = jpaRepository.findByStatus(eventStatus);

        // convert entities to domains using mapper !

        return null;
    }

}
