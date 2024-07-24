package com.github.kellyihyeon.stanceadmin.application.eventdeposit;

import com.github.kellyihyeon.stanceadmin.application.accounttransaction.dto.EventDepositCreation;
import com.github.kellyihyeon.stanceadmin.domain.eventdeposittransaction.EventDepositTransaction;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EventDepositTransactionService {

    public void saveEventDepositTransaction(EventDepositCreation serviceDto) {
        Long loggedInId = 999L;
        LocalDateTime now = LocalDateTime.now();

        List<EventDepositTransaction> transactions = serviceDto.toDomains(loggedInId, now);
    }
}
