package com.github.kellyihyeon.stanceadmin.infrastructure.repository.membershipfeedeposit;

import com.github.kellyihyeon.stanceadmin.application.accounttransaction.MembershipFeeDepositTransactionMapper;
import com.github.kellyihyeon.stanceadmin.domain.member.Member;
import com.github.kellyihyeon.stanceadmin.domain.membershipfeedeposit.MembershipFeeDepositRepository;
import com.github.kellyihyeon.stanceadmin.infrastructure.entity.membershipfeedeposit.MemberShipFeeDepositTransactionEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MembershipFeeDepositRepositoryImpl implements MembershipFeeDepositRepository {

    private final JpaMembershipFeeDepositTransactionEntityRepository jpaRepository;
    private final MembershipFeeDepositTransactionMapper mapper;


    @Override
    public List<Member> findPaidMembers(LocalDate startDate, LocalDate endDate) {
        List<MemberShipFeeDepositTransactionEntity> entities = jpaRepository.findByDueDateBetween(startDate, endDate);
        // TODO: mapper.toDomains() 구현
        return mapper.toDomains(entities);
    }
}
