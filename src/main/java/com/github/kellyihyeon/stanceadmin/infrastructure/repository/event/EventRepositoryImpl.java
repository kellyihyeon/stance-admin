package com.github.kellyihyeon.stanceadmin.infrastructure.repository.event;

import com.github.kellyihyeon.stanceadmin.application.event.EventMapper;
import com.github.kellyihyeon.stanceadmin.domain.event.Event;
import com.github.kellyihyeon.stanceadmin.domain.event.EventRepository;
import com.github.kellyihyeon.stanceadmin.domain.event.EventStatus;
import jakarta.persistence.EntityNotFoundException;
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
    public Event findByIdAndStatus(Long eventId, EventStatus status) {
        EventEntity eventEntity = jpaEventRepository.findByIdAndStatus(eventId, status).orElseThrow(() -> new EntityNotFoundException("존재하지 않는 이벤트예요."));
        return EventMapper.toDomain(eventEntity);
    }
}
