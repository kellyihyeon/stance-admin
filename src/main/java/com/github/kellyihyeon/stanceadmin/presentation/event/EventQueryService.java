package com.github.kellyihyeon.stanceadmin.presentation.event;

import com.github.kellyihyeon.stanceadmin.infrastructure.querydsl.EventProjection;
import com.github.kellyihyeon.stanceadmin.infrastructure.repository.event.EventQueryRepository;
import com.github.kellyihyeon.stanceadmin.models.EventResponse;
import com.github.kellyihyeon.stanceadmin.models.PagedEventResponse;
import com.github.kellyihyeon.stanceadmin.models.PagingMetadata;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EventQueryService {

    private final EventQueryRepository queryRepository;

    public PagedEventResponse getAllEvents(PageRequest pageable) {
        List<EventProjection> projections = queryRepository.getEvents(pageable);

        List<EventResponse> allEvents = projections.stream()
                .map(projection -> {
                    EventResponse response = new EventResponse();
                    response.eventId(projection.getId());
                    response.eventName(projection.getEventName());
                    response.description(projection.getEventMemo());
                    response.amount(projection.getAmount());
                    response.dueDate(projection.getDueDate().toString());
                    response.createdAt(Timestamp.valueOf(projection.getCreatedAt()));
                    response.creatorName(projection.getCreatorName());

                    return response;

                })
                .collect(Collectors.toList());

        PageImpl<EventResponse> pagedEvents = new PageImpl<>(allEvents, pageable, allEvents.size());
        PagingMetadata pagingMetadata = new PagingMetadata(
                pagedEvents.getNumber(),
                pagedEvents.getSize(),
                pagedEvents.getTotalElements(),
                pagedEvents.getTotalPages()
        );

        return new PagedEventResponse(allEvents, pagingMetadata);
    }
}
