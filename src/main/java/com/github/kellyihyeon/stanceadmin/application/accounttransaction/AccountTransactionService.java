package com.github.kellyihyeon.stanceadmin.application.accounttransaction;

import com.github.kellyihyeon.stanceadmin.application.account.AccountService;
import com.github.kellyihyeon.stanceadmin.application.accounttransaction.dto.MemberShipFeeDepositCreation;
import com.github.kellyihyeon.stanceadmin.domain.account.AccountRepository;
import com.github.kellyihyeon.stanceadmin.domain.accounttransaction.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountTransactionService {

    private final AccountTransactionRepository repository;
    private final AccountRepository accountRepository;

    private final AccountService accountService;


    @Transactional
    public void saveDepositAccountTransaction(TransactionIdentity transactionIdentity, Double amount) {
        // 유저 객체
        LocalDateTime now = LocalDateTime.now();
        Long loggedInId = 999L;

        Long defaultAccountId = accountService.getDefaultAccount().getId();
        AccountTransaction accountTransaction = AccountTransaction.create(
                defaultAccountId,
                transactionIdentity.getType(),
                transactionIdentity.getTransactionId(),
                transactionIdentity.getSubtype(),
                amount,
                now,
                loggedInId
        );

        Double balance = accountTransaction.addAmountToBalance(getLatestBalance());
        repository.createAccountTransaction(accountTransaction);

        accountService.updateBalance(balance);
    }

    private Double getLatestBalance() {
        return repository.findLatestAccountTransaction().getBalance();
    }

    @Transactional
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
        AccountTransaction accountTransaction = AccountTransaction.create(
                null,
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
