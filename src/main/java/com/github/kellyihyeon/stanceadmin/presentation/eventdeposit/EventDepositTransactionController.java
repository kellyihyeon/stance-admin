package com.github.kellyihyeon.stanceadmin.presentation.eventdeposit;

import com.github.kellyihyeon.stanceadmin.apis.EventFeeDepositTransactionApi;
import com.github.kellyihyeon.stanceadmin.application.eventdeposit.EventDepositTransactionService;
import com.github.kellyihyeon.stanceadmin.application.eventdeposit.dto.EventDepositCreation;
import com.github.kellyihyeon.stanceadmin.models.EventFeeDepositInput;
import com.github.kellyihyeon.stanceadmin.presentation.TimeConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequiredArgsConstructor
public class EventDepositTransactionController implements EventFeeDepositTransactionApi {

    private final EventDepositTransactionService eventDepositService;

    @Override
    public ResponseEntity<Void> saveEventFeeDepositTransaction(EventFeeDepositInput input) {
        EventDepositCreation serviceDto = EventDepositCreation.create(
                input.getEventId(),
                input.getDepositorIds(),
                input.getAmount(),
                TimeConverter.convertToLocalDate(input.getDepositDate()),
                input.getDescription()
        );

        eventDepositService.saveEventDepositTransaction(serviceDto);
        return ResponseEntity.created(URI.create("")).build();
    }
}
