package com.github.kellyihyeon.stanceadmin.application.eventdeposit;

import com.github.kellyihyeon.stanceadmin.application.accounttransaction.AccountTransactionService;
import com.github.kellyihyeon.stanceadmin.application.event.EventService;
import com.github.kellyihyeon.stanceadmin.application.eventapplicantregistry.EventApplicantRegistryService;
import com.github.kellyihyeon.stanceadmin.application.eventdeposit.dto.EventDepositCreation;
import com.github.kellyihyeon.stanceadmin.domain.accounttransaction.TransactionIdentity;
import com.github.kellyihyeon.stanceadmin.domain.accounttransaction.TransactionSubType;
import com.github.kellyihyeon.stanceadmin.domain.accounttransaction.TransactionType;
import com.github.kellyihyeon.stanceadmin.domain.eventdeposit.EventDepositTransaction;
import com.github.kellyihyeon.stanceadmin.domain.eventdeposit.EventDepositTransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EventDepositTransactionService {

    private final EventDepositTransactionRepository repository;
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

            accountTransactionService.saveAccountTransaction(
                    TransactionIdentity.create(transactionId, TransactionType.DEPOSIT, TransactionSubType.EVENT),
                    serviceDto.getAmount()
            );
        }
    }
}
