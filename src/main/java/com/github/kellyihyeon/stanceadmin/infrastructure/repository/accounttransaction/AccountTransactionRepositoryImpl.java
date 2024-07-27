package com.github.kellyihyeon.stanceadmin.infrastructure.repository.accounttransaction;

import com.github.kellyihyeon.stanceadmin.application.accounttransaction.AccountTransactionMapper;
import com.github.kellyihyeon.stanceadmin.application.accounttransaction.MembershipFeeDepositTransactionMapper;
import com.github.kellyihyeon.stanceadmin.domain.accounttransaction.AccountTransaction;
import com.github.kellyihyeon.stanceadmin.domain.accounttransaction.AccountTransactionRepository;
import com.github.kellyihyeon.stanceadmin.domain.membershipfeedeposit.MembershipFeeDepositTransaction;
import com.github.kellyihyeon.stanceadmin.infrastructure.entity.membershipfeedeposit.MemberShipFeeDepositTransactionEntity;
import com.github.kellyihyeon.stanceadmin.infrastructure.repository.membershipfeedeposit.JpaMembershipFeeDepositTransactionEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class AccountTransactionRepositoryImpl implements AccountTransactionRepository {

    private final JpaMembershipFeeDepositTransactionEntityRepository membershipFeeDepositRepository;
    private final JpaAccountTransactionEntityRepository jpaAccountTransactionRepository;

    private final MembershipFeeDepositTransactionMapper membershipFeeDepositTransactionMapper;


    @Override
    public Long createMembershipFeeDepositTransaction(MembershipFeeDepositTransaction domain) {
        MemberShipFeeDepositTransactionEntity entity = membershipFeeDepositTransactionMapper.toEntity(domain);
        return membershipFeeDepositRepository.save(entity).getId();
    }

    @Override
    public void saveAccountTransaction(AccountTransaction accountTransaction) {
        AccountTransactionEntity entity = AccountTransactionMapper.toEntity(accountTransaction);
        jpaAccountTransactionRepository.save(entity);
    }

    @Override
    public AccountTransaction findLatestAccountTransaction() {
        AccountTransactionEntity entity = jpaAccountTransactionRepository.findFirstByOrderByCreatedAtDesc();
        return AccountTransactionMapper.toDomain(entity);
    }
}
