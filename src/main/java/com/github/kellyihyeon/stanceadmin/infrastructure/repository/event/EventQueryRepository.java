package com.github.kellyihyeon.stanceadmin.infrastructure.repository.event;

import com.github.kellyihyeon.stanceadmin.domain.event.Event;
import com.github.kellyihyeon.stanceadmin.domain.event.EventStatus;
import com.github.kellyihyeon.stanceadmin.infrastructure.querydsl.EventProjection;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface EventQueryRepository {

    List<EventProjection> getEvents(PageRequest pageable);

    long countTotalElements();

    List<Event> getEventsByStatus(EventStatus eventStatus);

}
