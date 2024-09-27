package com.github.kellyihyeon.stanceadmin.presentation.event;

import com.github.kellyihyeon.stanceadmin.apis.EventApi;
import com.github.kellyihyeon.stanceadmin.application.event.EventService;
import com.github.kellyihyeon.stanceadmin.application.event.dto.EventCreation;
import com.github.kellyihyeon.stanceadmin.domain.event.EventItem;
import com.github.kellyihyeon.stanceadmin.domain.event.EventStatus;
import com.github.kellyihyeon.stanceadmin.models.*;
import com.github.kellyihyeon.stanceadmin.presentation.TimeConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class EventController implements EventApi {

    private final EventService service;


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
    public ResponseEntity<List<PagedEventResponse>> getAllEvents() {
        return null;
    }

    @Override
    public ResponseEntity<List<EventSummaryResponse>> getEventsByStatus(EventStatusEnum status) {
        EventStatus eventStatus = EventStatus.valueOf(status.getValue());
        List<EventSummaryResponse> result = service.getEventsByStatus(eventStatus);

        return ResponseEntity.ok(result);
    }
}
