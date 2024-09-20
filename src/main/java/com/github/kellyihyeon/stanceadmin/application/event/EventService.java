package com.github.kellyihyeon.stanceadmin.application.event;

import com.github.kellyihyeon.stanceadmin.application.event.dto.EventCreation;
import com.github.kellyihyeon.stanceadmin.domain.event.Event;
import com.github.kellyihyeon.stanceadmin.domain.event.EventRepository;
import com.github.kellyihyeon.stanceadmin.domain.event.EventStatus;
import com.github.kellyihyeon.stanceadmin.models.EventSummaryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository repository;
    private final EventMapper mapper;

    public void createEvent(EventCreation serviceDto) {
        LocalDateTime now = LocalDateTime.now();
        Long loggedInId = 999L;

        Event event = mapper.toDomain(serviceDto, now, loggedInId);
        repository.createEvent(event);
    }

    public boolean existsActiveEvent(Long eventId) {
        return repository.existsByIdAndStatus(eventId, EventStatus.ACTIVE);
    }

    public List<EventSummaryResponse> getEventsByStatus(EventStatus eventStatus) {
        List<Event> events = repository.getEventsByStatus(eventStatus);

        // convert domain to response dto !

        return null;
    }
}
