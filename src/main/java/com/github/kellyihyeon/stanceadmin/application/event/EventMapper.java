package com.github.kellyihyeon.stanceadmin.application.event;

import com.github.kellyihyeon.stanceadmin.application.event.dto.EventCreation;
import com.github.kellyihyeon.stanceadmin.domain.event.Event;
import com.github.kellyihyeon.stanceadmin.infrastructure.repository.event.EventEntity;

public class EventMapper {

    public static Event toDomain(EventCreation eventCreation) {
        return Event.create(
                null,
                eventCreation.eventItem(),
                eventCreation.amount(),
                eventCreation.dueDate(),
                eventCreation.description(),
                eventCreation.status()
        );
    }

    public static EventEntity toEntity(Event event) {
        return new EventEntity(
                event.getEventItem(),
                event.getAmount(),
                event.getDueDate(),
                event.getDescription(),
                event.getStatus()
        );
    }

    public static Event toDomain(EventEntity eventEntity) {
        return Event.create(
                eventEntity.getId(),
                eventEntity.getEventItem(),
                eventEntity.getAmount(),
                eventEntity.getDueDate(),
                eventEntity.getDescription(),
                eventEntity.getStatus()
        );
    }
}
