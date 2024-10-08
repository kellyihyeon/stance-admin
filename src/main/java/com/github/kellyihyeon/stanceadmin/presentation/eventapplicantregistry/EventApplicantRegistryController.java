package com.github.kellyihyeon.stanceadmin.presentation.eventapplicantregistry;

import com.github.kellyihyeon.stanceadmin.apis.EventApplicantApi;
import com.github.kellyihyeon.stanceadmin.application.eventapplicantregistry.EventApplicantQueryService;
import com.github.kellyihyeon.stanceadmin.application.eventapplicantregistry.EventApplicantRegistryService;
import com.github.kellyihyeon.stanceadmin.application.eventapplicantregistry.dto.EventApplicantRegistryCreation;
import com.github.kellyihyeon.stanceadmin.models.EventApplicantInput;
import com.github.kellyihyeon.stanceadmin.models.EventApplicantResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class EventApplicantRegistryController implements EventApplicantApi {

    private final EventApplicantRegistryService eventApplicantRegistryService;
    private final EventApplicantQueryService queryService;

    @Override
    public ResponseEntity<List<EventApplicantResponse>> getEventApplicantsByEventId(Long eventId) {
        List<EventApplicantResponse> result = queryService.getApplicantsByEventId(eventId);
        return ResponseEntity.ok(result);
    }

    @Override
    public ResponseEntity<Void> saveEventApplicant(EventApplicantInput eventApplicantInput) {
        EventApplicantRegistryCreation eventApplicantRegistryCreation = new EventApplicantRegistryCreation(
                eventApplicantInput.getEventId(),
                eventApplicantInput.getApplicantIds(),
                eventApplicantInput.getDescription()
        );

        eventApplicantRegistryService.createEventApplicant(eventApplicantRegistryCreation);
        return ResponseEntity.created(URI.create("")).build();
    }
}
