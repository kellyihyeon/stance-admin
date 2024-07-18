package com.github.kellyihyeon.stanceadmin.application.accounttransaction;

import com.github.kellyihyeon.stanceadmin.application.accounttransaction.dto.MemberShipFeeDepositCreation;
import com.github.kellyihyeon.stanceadmin.domain.accounttransaction.AccountTransactionRepository;
import com.github.kellyihyeon.stanceadmin.domain.accounttransaction.MembershipFeeDepositTransaction;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountTransactionService {

    private final AccountTransactionRepository repository;


    public void createMembershipFeeDepositTransaction(MemberShipFeeDepositCreation serviceDto) {
        List<MembershipFeeDepositTransaction> transactions = serviceDto.toDomain();

        for (MembershipFeeDepositTransaction domain : transactions) {
            repository.createMembershipFeeDepositTransaction(domain);
        }
    }
}
