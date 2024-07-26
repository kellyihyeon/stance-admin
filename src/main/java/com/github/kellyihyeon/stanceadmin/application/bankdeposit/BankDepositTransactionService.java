package com.github.kellyihyeon.stanceadmin.application.bankdeposit;

import com.github.kellyihyeon.stanceadmin.application.bankdeposit.dto.BankDepositCreation;
import com.github.kellyihyeon.stanceadmin.domain.bankdeposit.BankDepositTransaction;
import com.github.kellyihyeon.stanceadmin.domain.bankdeposit.BankDepositTransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class BankDepositTransactionService {

    private final BankDepositTransactionRepository repository;
    private final BankDepositTransactionMapper mapper;

    public void saveBankDepositTransaction(BankDepositCreation serviceDto) {
        Long loggedInId = 999L;
        LocalDateTime now = LocalDateTime.now();

        BankDepositTransaction bankDepositTransaction = mapper.toDomain(serviceDto, loggedInId, now);
        Long transactionId = repository.saveBankDepositTransaction(bankDepositTransaction).getId();
    }
}
