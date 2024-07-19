package com.github.kellyihyeon.stanceadmin.infrastructure.repository.accounttransaction;

import com.github.kellyihyeon.stanceadmin.application.accounttransaction.AccountTransactionMapper;
import com.github.kellyihyeon.stanceadmin.application.accounttransaction.MembershipFeeDepositTransactionMapper;
import com.github.kellyihyeon.stanceadmin.domain.accounttransaction.AccountTransaction;
import com.github.kellyihyeon.stanceadmin.domain.accounttransaction.AccountTransactionRepository;
import com.github.kellyihyeon.stanceadmin.domain.accounttransaction.MembershipFeeDepositTransaction;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class AccountTransactionRepositoryImpl implements AccountTransactionRepository {

    private final JpaMembershipFeeDepositTransactionEntityRepository membershipFeeDepositRepository;
    private final JpaAccountTransactionEntityRepository jpaAccountTransactionRepository;


    @Override
    public Long createMembershipFeeDepositTransaction(MembershipFeeDepositTransaction domain) {
        MemberShipFeeDepositTransactionEntity entity = MembershipFeeDepositTransactionMapper.toEntity(domain);
        return membershipFeeDepositRepository.save(entity).getId();
    }

    @Override
    public void createAccountTransaction(AccountTransaction accountTransaction) {
        AccountTransactionEntity entity = AccountTransactionMapper.toEntity(accountTransaction);
        jpaAccountTransactionRepository.save(entity);
    }
}
