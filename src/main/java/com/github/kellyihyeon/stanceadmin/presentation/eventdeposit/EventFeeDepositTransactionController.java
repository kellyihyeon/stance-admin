package com.github.kellyihyeon.stanceadmin.presentation.eventdeposit;

import com.github.kellyihyeon.stanceadmin.apis.EventFeeDepositTransactionApi;
import com.github.kellyihyeon.stanceadmin.application.eventdeposit.EventFeeDepositTransactionService;
import com.github.kellyihyeon.stanceadmin.application.eventdeposit.dto.EventDepositCreation;
import com.github.kellyihyeon.stanceadmin.models.EventApplicantResponse;
import com.github.kellyihyeon.stanceadmin.models.EventFeeDepositInput;
import com.github.kellyihyeon.stanceadmin.presentation.TimeConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class EventFeeDepositTransactionController implements EventFeeDepositTransactionApi {

    private final EventFeeDepositTransactionService service;

    @Override
    public ResponseEntity<List<EventApplicantResponse>> getEventFeeDepositStatus(Long eventId) {
        List<EventApplicantResponse> result = service.getApplicantsForEvent(eventId);
        return ResponseEntity.ok(result);
    }

    @Override
    public ResponseEntity<Void> saveEventFeeDepositTransaction(EventFeeDepositInput input) {
        EventDepositCreation serviceDto = EventDepositCreation.create(
                input.getEventId(),
                input.getDepositorIds(),
                input.getAmount(),
                TimeConverter.convertToLocalDate(input.getDepositDate()),
                input.getDescription()
        );

        service.saveEventDepositTransaction(serviceDto);
        return ResponseEntity.created(URI.create("")).build();
    }
}
