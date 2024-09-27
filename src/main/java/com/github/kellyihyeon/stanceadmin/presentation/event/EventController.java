package com.github.kellyihyeon.stanceadmin.presentation.event;

import com.github.kellyihyeon.stanceadmin.apis.EventApi;
import com.github.kellyihyeon.stanceadmin.application.event.EventService;
import com.github.kellyihyeon.stanceadmin.application.event.dto.EventCreation;
import com.github.kellyihyeon.stanceadmin.domain.event.EventItem;
import com.github.kellyihyeon.stanceadmin.domain.event.EventStatus;
import com.github.kellyihyeon.stanceadmin.models.EventInput;
import com.github.kellyihyeon.stanceadmin.models.EventStatusEnum;
import com.github.kellyihyeon.stanceadmin.models.EventSummaryResponse;
import com.github.kellyihyeon.stanceadmin.models.PagedEventResponse;
import com.github.kellyihyeon.stanceadmin.presentation.TimeConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class EventController implements EventApi {

    private final EventService service;
    private final EventQueryService queryService;


    @Override
    public ResponseEntity<Void> addEvent(EventInput eventInput) {
        EventCreation eventCreation = new EventCreation(
                EventItem.valueOf(eventInput.getEventItem().getValue()),
                eventInput.getAmount(),
                TimeConverter.convertToLocalDate(eventInput.getDueDate()),
                eventInput.getDescription(),
                EventStatus.valueOf(eventInput.getStatus().getValue())
        );

        service.createEvent(eventCreation);
        return ResponseEntity.created(URI.create("")).build();
    }

    @Override
    public ResponseEntity<PagedEventResponse> getAllEvents(Integer page, Integer size) {
        PageRequest pageable = PageRequest.of(page - 1, size);
        PagedEventResponse response = queryService.getAllEvents(pageable);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<List<EventSummaryResponse>> getEventsByStatus(EventStatusEnum status) {
        EventStatus eventStatus = EventStatus.valueOf(status.getValue());
        List<EventSummaryResponse> result = service.getEventsByStatus(eventStatus);

        return ResponseEntity.ok(result);
    }
}
