package com.github.kellyihyeon.stanceadmin.application.eventdeposit;

import com.github.kellyihyeon.stanceadmin.application.accounttransaction.AccountTransactionService;
import com.github.kellyihyeon.stanceadmin.application.event.EventService;
import com.github.kellyihyeon.stanceadmin.application.eventapplicantregistry.EventApplicantRegistryService;
import com.github.kellyihyeon.stanceadmin.application.eventdeposit.dto.EventDepositCreation;
import com.github.kellyihyeon.stanceadmin.domain.accounttransaction.TransactionIdentity;
import com.github.kellyihyeon.stanceadmin.domain.accounttransaction.TransactionSubType;
import com.github.kellyihyeon.stanceadmin.domain.accounttransaction.TransactionType;
import com.github.kellyihyeon.stanceadmin.domain.eventapplicantregistry.EventApplicantDepositRegistry;
import com.github.kellyihyeon.stanceadmin.domain.eventdeposit.EventDepositTransaction;
import com.github.kellyihyeon.stanceadmin.domain.eventdeposit.EventFeeDepositTransactionRepository;
import com.github.kellyihyeon.stanceadmin.models.EventFeeDepositResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EventFeeDepositTransactionService {

    private final EventFeeDepositTransactionRepository repository;
    private final EventDepositTransactionMapper mapper;

    private final AccountTransactionService accountTransactionService;
    private final EventService eventService;

    private final EventApplicantRegistryService eventApplicantRegistryService;



    @Transactional
    public void saveEventDepositTransaction(EventDepositCreation serviceDto) {
        if (!eventService.existsActiveEvent(serviceDto.getEventId())) {
            throw new IllegalArgumentException("존재하지 않는 이벤트예요.");
        }

        Long loggedInId = 999L;
        LocalDateTime now = LocalDateTime.now();
        List<EventDepositTransaction> transactions = mapper.toDomains(serviceDto, loggedInId, now);

        for (EventDepositTransaction eventDepositTransaction : transactions) {
            Long transactionId = repository.saveEventDepositTransaction(
                    EventDepositTransaction.createWithoutId(
                            eventDepositTransaction.getEventId(),
                            eventDepositTransaction.getDepositorId(),
                            eventDepositTransaction.getAmount(),
                            eventDepositTransaction.getDepositDate(),
                            eventDepositTransaction.getDescription(),
                            loggedInId,
                            now
                    )
            );

            eventApplicantRegistryService.processDepositCompletion(serviceDto.getEventId(), serviceDto.getDepositorIds());

            // TODO: repository 에 저장된 entity 의 deposit date 를 파라미터로 넘길 것
            accountTransactionService.saveAccountTransaction(
                    TransactionIdentity.create(
                            transactionId,
                            TransactionType.DEPOSIT,
                            TransactionSubType.EVENT,
                            eventDepositTransaction.getDepositDate()
                            ),
                    serviceDto.getAmount()
            );
        }
    }

    public List<EventFeeDepositResponse> getEventFeeDepositStatus(Long eventId) {
        if (!eventService.existsActiveEvent(eventId)) {
            throw new IllegalArgumentException("존재하지 않는 이벤트예요.");
        }

        List<EventApplicantDepositRegistry> registries = repository.getEventApplicantRegistriesByEventId(eventId);

        return registries.stream()
                .map(
                        registry -> new EventFeeDepositResponse(
                                registry.getEventDescription(),
                                registry.getMemberName(),
                                registry.getAmount(),
                                registry.getDepositStatus().getDisplayName(),
                                registry.getDueDate().toString(),
                                registry.getDepositDate() != null ? registry.getDepositDate().toString() : null
                        )
                )
                .collect(Collectors.toList());
    }
}
