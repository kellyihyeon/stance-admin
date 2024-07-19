package com.github.kellyihyeon.stanceadmin.presentation.event;

import com.github.kellyihyeon.stanceadmin.apis.EventApi;
import com.github.kellyihyeon.stanceadmin.application.event.EventService;
import com.github.kellyihyeon.stanceadmin.application.event.dto.EventCreation;
import com.github.kellyihyeon.stanceadmin.domain.event.EventItem;
import com.github.kellyihyeon.stanceadmin.domain.event.EventStatus;
import com.github.kellyihyeon.stanceadmin.models.EventInput;
import com.github.kellyihyeon.stanceadmin.models.EventName;
import com.github.kellyihyeon.stanceadmin.models.Events;
import com.github.kellyihyeon.stanceadmin.presentation.TimeConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class EventController implements EventApi {

    private final EventService eventService;


    @Override
    public ResponseEntity<Void> addEvent(EventInput eventInput) {
        EventCreation eventCreation = new EventCreation(
                EventItem.valueOf(eventInput.getEventItem().getValue()),
                eventInput.getAmount(),
                TimeConverter.convertToLocalDate(eventInput.getDueDate()),
                eventInput.getDescription(),
                EventStatus.valueOf(eventInput.getStatus().getValue())
        );

        eventService.createEvent(eventCreation);
        return ResponseEntity.created(URI.create("")).build();
    }

    @Override
    public ResponseEntity<List<Events>> getAllEvents() {
        return null;
    }

    @Override
    public ResponseEntity<List<EventName>> getEventsByStatus(String status) {
        return null;
    }
}
