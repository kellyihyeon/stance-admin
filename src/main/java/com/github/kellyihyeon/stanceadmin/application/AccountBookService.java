package com.github.kellyihyeon.stanceadmin.application;

import com.github.kellyihyeon.stanceadmin.application.dto.MembershipFeeByMember;
import com.github.kellyihyeon.stanceadmin.domain.accountbook.AccountBook;
import com.github.kellyihyeon.stanceadmin.domain.accountbook.TransactionType;
import com.github.kellyihyeon.stanceadmin.domain.deposit.Deposit;
import com.github.kellyihyeon.stanceadmin.infrastructure.repository.accountbook.AccountBookRepository;
import com.github.kellyihyeon.stanceadmin.infrastructure.repository.deposit.DepositRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountBookService {

    private final AccountBookRepository accountBookRepository;
    private final DepositRepository depositRepository;


    @Transactional
    public void registerMembershipFeeByMember(MembershipFeeByMember membershipFeeByMember) {
        List<Deposit> deposits = Deposit.toEntityList(membershipFeeByMember);

        for (Deposit deposit : deposits) {
            Long depositId = depositRepository.save(deposit).getId();

            AccountBook accountBook = accountBookRepository.findTopByOrderByIdDesc().orElseThrow(() -> new IllegalStateException("입출금 데이터가 없습니다."));
            log.debug("스탠스 가계부 최신 데이터 [{}]의 잔액 [{}]", accountBook.getId(), accountBook.getBalance());

            AccountBook updatedAccountBook = accountBook.updateBalance(
                    depositId,
                    TransactionType.DEPOSIT,
                    deposit.getDepositDate(),
                    deposit.getDepositor(),
                    deposit.getAmount()
            );

            accountBookRepository.save(updatedAccountBook);
        }
    }
}
