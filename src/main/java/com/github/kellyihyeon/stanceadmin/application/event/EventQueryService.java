package com.github.kellyihyeon.stanceadmin.application.event;

import com.github.kellyihyeon.stanceadmin.domain.event.Event;
import com.github.kellyihyeon.stanceadmin.domain.event.EventStatus;
import com.github.kellyihyeon.stanceadmin.infrastructure.querydsl.EventProjection;
import com.github.kellyihyeon.stanceadmin.infrastructure.repository.event.EventQueryRepository;
import com.github.kellyihyeon.stanceadmin.models.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EventQueryService {

    private final EventQueryRepository queryRepository;

    public PagedEventResponse getAllEvents(PageRequest pageable) {
        long totalElements = queryRepository.countTotalElements();
        List<EventProjection> projections = queryRepository.getEvents(pageable);

        List<EventResponse> allEvents = projections.stream()
                .map(projection -> {
                    EventResponse response = new EventResponse();
                    response.eventId(projection.id());
                    response.eventName(projection.eventItem().getDisplayName());
                    response.description(projection.eventMemo());
                    response.amount(BigDecimal.valueOf(projection.amount()));
                    response.dueDate(projection.dueDate().toString());
                    response.status(EventStatusEnum.valueOf(projection.status().name()));
                    response.createdAt(Timestamp.valueOf(projection.createdAt()));
                    response.creatorName(projection.creatorName());

                    return response;

                })
                .collect(Collectors.toList());

        PageImpl<EventResponse> pagedEvents = new PageImpl<>(allEvents, pageable, totalElements);
        PagingMetadata pagingMetadata = new PagingMetadata(
                pagedEvents.getNumber(),
                pagedEvents.getSize(),
                pagedEvents.getTotalElements(),
                pagedEvents.getTotalPages()
        );

        return new PagedEventResponse(allEvents, pagingMetadata);
    }

    public List<EventSummaryResponse> getEventsByStatus(EventStatus eventStatus) {
        List<Event> events = queryRepository.getEventsByStatus(eventStatus);

        List<EventSummaryResponse> result = new ArrayList<>();
        events.forEach(event -> {
                    result.add(
                            new EventSummaryResponse(
                                    event.getId(),
                                    event.getEventItem().getDisplayName(),
                                    event.getDescription()
                            )
                    );
                }
        );

        return result;
    }
}
