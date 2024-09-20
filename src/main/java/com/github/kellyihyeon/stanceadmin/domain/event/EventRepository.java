package com.github.kellyihyeon.stanceadmin.domain.event;

import java.util.List;

public interface EventRepository {

    void createEvent(Event event);

    boolean existsByIdAndStatus(Long eventId, EventStatus status);

    List<Event> getEventsByStatus(EventStatus eventStatus);

}
