package com.github.kellyihyeon.stanceadmin.infrastructure.repository.accounttransaction;

import com.github.kellyihyeon.stanceadmin.domain.accounttransaction.AccountTransactionRepository;
import com.github.kellyihyeon.stanceadmin.domain.accounttransaction.MembershipFeeDepositTransaction;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class AccountTransactionRepositoryImpl implements AccountTransactionRepository {

    private final JpaMembershipFeeDepositTransactionEntityRepository membershipFeeDepositRepository;


    @Override
    public void createMembershipFeeDepositTransaction(MembershipFeeDepositTransaction domain) {
        // convert domain to entity

        membershipFeeDepositRepository.save(new MemberShipFeeDepositTransactionEntity());
    }
}
