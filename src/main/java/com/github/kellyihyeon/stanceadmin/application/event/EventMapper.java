package com.github.kellyihyeon.stanceadmin.application.event;

import com.github.kellyihyeon.stanceadmin.application.event.dto.EventCreation;
import com.github.kellyihyeon.stanceadmin.domain.event.Event;
import com.github.kellyihyeon.stanceadmin.infrastructure.entity.event.EventEntity;

import java.time.LocalDateTime;
import java.util.List;

public interface EventMapper {

    Event toDomain(EventCreation serviceDto, LocalDateTime now, Long loggedInId);

    EventEntity toEntity(Event event);

    Event toDomain(EventEntity entity);

    List<Event> toDomains(List<EventEntity> entities);

}
