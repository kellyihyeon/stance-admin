package com.github.kellyihyeon.stanceadmin.application.accounttransaction;

import com.github.kellyihyeon.stanceadmin.application.accounttransaction.dto.MemberShipFeeDepositCreation;
import com.github.kellyihyeon.stanceadmin.domain.accounttransaction.AccountTransactionRepository;
import com.github.kellyihyeon.stanceadmin.domain.accounttransaction.MembershipFeeDepositTransaction;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountTransactionService {

    private final AccountTransactionRepository repository;


    public void createMembershipFeeDepositTransaction(MemberShipFeeDepositCreation serviceDto) {
        MembershipFeeDepositTransaction domain = serviceDto.toDomain();
        repository.createMembershipFeeDepositTransaction(domain);
    }
}
