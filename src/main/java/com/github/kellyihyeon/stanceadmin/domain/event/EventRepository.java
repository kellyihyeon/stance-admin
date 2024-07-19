package com.github.kellyihyeon.stanceadmin.domain.event;

public interface EventRepository {

    void createEvent(Event event);

    Event findByIdAndStatus(Long eventId, EventStatus status);
}
