package com.github.kellyihyeon.stanceadmin.infrastructure.repository.event;

import com.github.kellyihyeon.stanceadmin.application.event.EventMapper;
import com.github.kellyihyeon.stanceadmin.domain.event.Event;
import com.github.kellyihyeon.stanceadmin.domain.event.EventRepository;
import com.github.kellyihyeon.stanceadmin.domain.event.EventStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class EventRepositoryImpl implements EventRepository {

    private final JpaEventRepository jpaEventRepository;

    @Override
    public void createEvent(Event event) {
        EventEntity entity = EventMapper.toEntity(event);
        jpaEventRepository.save(entity);
    }

    @Override
    public boolean existsByIdAndStatus(Long eventId, EventStatus status) {
        return jpaEventRepository.existsByIdAndStatus(eventId, status);
    }
}
