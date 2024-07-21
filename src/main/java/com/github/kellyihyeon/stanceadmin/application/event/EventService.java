package com.github.kellyihyeon.stanceadmin.application.event;

import com.github.kellyihyeon.stanceadmin.application.event.dto.EventCreation;
import com.github.kellyihyeon.stanceadmin.domain.event.Event;
import com.github.kellyihyeon.stanceadmin.domain.event.EventRepository;
import com.github.kellyihyeon.stanceadmin.domain.event.EventStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;

    public void createEvent(EventCreation eventCreation) {
        Event event = EventMapper.toDomain(eventCreation);
        eventRepository.createEvent(event);
    }

    public boolean existsActiveEvent(Long eventId) {
        return eventRepository.existsByIdAndStatus(eventId, EventStatus.ACTIVE);
    }
}
