package com.github.kellyihyeon.stanceadmin.presentation.eventapplicant;

import com.github.kellyihyeon.stanceadmin.apis.EventApplicantApi;
import com.github.kellyihyeon.stanceadmin.application.eventapplicant.EventApplicantService;
import com.github.kellyihyeon.stanceadmin.application.eventapplicant.dto.EventApplicantCreation;
import com.github.kellyihyeon.stanceadmin.models.EventApplicantInput;
import com.github.kellyihyeon.stanceadmin.models.EventPayers;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class EventApplicantController implements EventApplicantApi {

    private final EventApplicantService eventApplicantService;

    @Override
    public ResponseEntity<List<EventPayers>> getEventPayersByDepositStatus(String depositStatus) {
        return null;
    }

    @Override
    public ResponseEntity<Void> saveEventApplicant(EventApplicantInput eventApplicantInput) {
        EventApplicantCreation eventApplicantCreation = new EventApplicantCreation(
                eventApplicantInput.getEventId(),
                eventApplicantInput.getApplicantIds(),
                eventApplicantInput.getDescription()
        );

        eventApplicantService.createEventApplicant(eventApplicantCreation);
        return ResponseEntity.created(URI.create("")).build();
    }
}
