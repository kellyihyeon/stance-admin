package com.github.kellyihyeon.stanceadmin.application.cardpayment;

import com.github.kellyihyeon.stanceadmin.application.accounttransaction.AccountTransactionService;
import com.github.kellyihyeon.stanceadmin.application.cardpayment.dto.CardPaymentCreation;
import com.github.kellyihyeon.stanceadmin.domain.accounttransaction.TransactionIdentity;
import com.github.kellyihyeon.stanceadmin.domain.accounttransaction.TransactionSubType;
import com.github.kellyihyeon.stanceadmin.domain.accounttransaction.TransactionType;
import com.github.kellyihyeon.stanceadmin.domain.cardpayment.CardPaymentTransaction;
import com.github.kellyihyeon.stanceadmin.domain.cardpayment.CardPaymentTransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CardPaymentTransactionService {

    private final CardPaymentTransactionRepository repository;
    private final CardPaymentTransactionMapper mapper;

    private final AccountTransactionService accountTransactionService;

    @Transactional
    public void saveCardPaymentTransaction(CardPaymentCreation serviceDto) {
        Long loggedInId = 999L;
        LocalDateTime now = LocalDateTime.now();

        CardPaymentTransaction cardPaymentTransaction = repository.save(mapper.toDomain(serviceDto, loggedInId, now));

        TransactionIdentity transactionIdentity = TransactionIdentity.create(
                cardPaymentTransaction.getId(),
                TransactionType.WITHDRAW,
                TransactionSubType.CARD_PAYMENT,
                cardPaymentTransaction.getExpenseDate()
        );
        accountTransactionService.saveAccountTransaction(transactionIdentity, cardPaymentTransaction.getAmount().doubleValue());
    }
}
