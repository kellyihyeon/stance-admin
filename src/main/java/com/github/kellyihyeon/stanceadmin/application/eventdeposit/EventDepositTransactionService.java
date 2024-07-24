package com.github.kellyihyeon.stanceadmin.application.eventdeposit;

import com.github.kellyihyeon.stanceadmin.application.accounttransaction.AccountTransactionService;
import com.github.kellyihyeon.stanceadmin.application.eventdeposit.dto.EventDepositCreation;
import com.github.kellyihyeon.stanceadmin.domain.eventdeposit.EventDepositTransaction;
import com.github.kellyihyeon.stanceadmin.domain.eventdeposit.EventDepositTransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EventDepositTransactionService {

    private final EventDepositTransactionRepository repository;
    private final AccountTransactionService accountTransactionService;


    public void saveEventDepositTransaction(EventDepositCreation serviceDto) {
        Long loggedInId = 999L;
        LocalDateTime now = LocalDateTime.now();

        List<EventDepositTransaction> transactions = serviceDto.toDomains(loggedInId, now);

        for (EventDepositTransaction eventDepositTransaction : transactions) {
            Long transactionId = repository.saveEventDepositTransaction(
                    new EventDepositTransaction(
                            eventDepositTransaction.getId(),
                            eventDepositTransaction.getEventId(),
                            eventDepositTransaction.getApplicantId(),
                            eventDepositTransaction.getAmount(),
                            eventDepositTransaction.getDepositDate(),
                            eventDepositTransaction.getDescription(),
                            loggedInId,
                            now
                    )
            );
            // TODO: transactionType, transactionSubType 수정
            accountTransactionService.createAccountTransaction(transactionId);
       }
    }
}
