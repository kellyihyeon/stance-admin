package com.github.kellyihyeon.stanceadmin.application.event;

import com.github.kellyihyeon.stanceadmin.application.event.dto.EventCreation;
import com.github.kellyihyeon.stanceadmin.domain.event.Event;
import com.github.kellyihyeon.stanceadmin.infrastructure.entity.event.EventEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class EventMapperImpl implements EventMapper {

    @Override
    public Event toDomain(EventCreation serviceDto, LocalDateTime createdAt, Long creatorId) {
        return Event.createWithId(
                null,
                serviceDto.eventItem(),
                serviceDto.amount(),
                serviceDto.dueDate(),
                serviceDto.description(),
                serviceDto.status(),
                creatorId,
                createdAt
        );
    }

    @Override
    public EventEntity toEntity(Event event) {
        return new EventEntity(
                event.getEventItem(),
                event.getAmount(),
                event.getDueDate(),
                event.getDescription(),
                event.getStatus(),
                event.getCreatedAt(),
                event.getCreatorId(
                ));
    }

    @Override
    public Event toDomain(EventEntity entity) {
        return Event.createWithId(
                entity.getId(),
                entity.getEventItem(),
                entity.getAmount(),
                entity.getDueDate(),
                entity.getDescription(),
                entity.getStatus(),
                entity.getCreatorId(),
                entity.getCreatedAt()
        );
    }

    @Override
    public List<Event> toDomains(List<EventEntity> entities) {
        List<Event> result = new ArrayList<>();
        entities
                .forEach(entity ->
                        result.add(toDomain(entity))
                );

        return result;
    }
}
