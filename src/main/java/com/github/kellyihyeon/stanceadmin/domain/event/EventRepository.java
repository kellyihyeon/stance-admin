package com.github.kellyihyeon.stanceadmin.domain.event;

public interface EventRepository {

    void createEvent(Event event);

    boolean existsByIdAndStatus(Long eventId, EventStatus status);
}
