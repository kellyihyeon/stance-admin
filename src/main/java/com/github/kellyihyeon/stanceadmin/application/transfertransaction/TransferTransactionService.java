package com.github.kellyihyeon.stanceadmin.application.transfertransaction;

import com.github.kellyihyeon.stanceadmin.application.transfertransaction.dto.TransferCreation;
import com.github.kellyihyeon.stanceadmin.domain.transfertransaction.TransferTransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TransferTransactionService {

    private final TransferTransactionRepository repository;
    private final TransferTransactionMapper mapper;

    public void saveTransferTransaction(TransferCreation serviceDto) {
        LocalDateTime now = LocalDateTime.now();
        Long loggedInId = 999L;

        repository.save(mapper.toDomain(serviceDto, loggedInId, now));
    }
}
