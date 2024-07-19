package com.github.kellyihyeon.stanceadmin.application.accounttransaction;

import com.github.kellyihyeon.stanceadmin.application.accounttransaction.dto.MemberShipFeeDepositCreation;
import com.github.kellyihyeon.stanceadmin.domain.account.AccountRepository;
import com.github.kellyihyeon.stanceadmin.domain.accounttransaction.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountTransactionService {

    private final AccountTransactionRepository repository;
    private final AccountRepository accountRepository;


    public void createMembershipFeeDepositTransaction(MemberShipFeeDepositCreation serviceDto) {
        List<MembershipFeeDepositTransaction> transactions = serviceDto.toDomain();

        for (MembershipFeeDepositTransaction domain : transactions) {
            Long transactionId = repository.createMembershipFeeDepositTransaction(domain);
            this.createAccountTransaction(transactionId);
        }
    }

    public void createAccountTransaction(Long transactionId) {
        LocalDateTime now = LocalDateTime.now();
        Long loggedInId = 999L;

        Long defaultAccountId = accountRepository.getDefaultAccount().getId();
        AccountTransaction accountTransaction = new AccountTransaction(
                defaultAccountId,
                TransactionType.DEPOSIT,
                transactionId,
                TransactionSubType.MEMBERSHIP_FEE,
                now,
                loggedInId
        );
        repository.createAccountTransaction(accountTransaction);
    }
}
