package com.github.kellyihyeon.stanceadmin.application.accountbook;

import com.github.kellyihyeon.stanceadmin.application.accountbook.dto.MembershipFeeByGuest;
import com.github.kellyihyeon.stanceadmin.application.accountbook.dto.MembershipFeeForm;
import com.github.kellyihyeon.stanceadmin.application.deposit.dto.ExtraFee;
import com.github.kellyihyeon.stanceadmin.application.member.dto.MemberIdAndName;
import com.github.kellyihyeon.stanceadmin.domain.accountbook.AccountBook;
import com.github.kellyihyeon.stanceadmin.domain.accountbook.TransactionType;
import com.github.kellyihyeon.stanceadmin.domain.deposit.Deposit;
import com.github.kellyihyeon.stanceadmin.domain.member.MemberType;
import com.github.kellyihyeon.stanceadmin.domain.member.MembershipFeeType;
import com.github.kellyihyeon.stanceadmin.infrastructure.repository.accountbook.AccountBookRepository;
import com.github.kellyihyeon.stanceadmin.infrastructure.repository.deposit.DepositRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountBookService {

    private final AccountBookRepository accountBookRepository;
    private final DepositRepository depositRepository;


    @Transactional
    public void registerMembershipFeeByMember(MembershipFeeForm membershipFeeForm) {
        List<Deposit> deposits = Deposit.toEntityList(membershipFeeForm);
        saveDeposits(deposits);
    }

    @Transactional
    public void registerMembershipFeeByGuest(MembershipFeeByGuest membershipFeeByGuest) {
        // TODO. 회원으로 등록하기
        List<MemberIdAndName> members = new LinkedList<>();
        members.add(new MemberIdAndName(998L, "coffee"));

        List<Deposit> deposits = Deposit.toEntityList(
                new MembershipFeeForm(
                        MemberType.GUEST,
                        MembershipFeeType.ONE_DAY,
                        membershipFeeByGuest.getAmount(),
                        membershipFeeByGuest.getDueMonth(),
                        membershipFeeByGuest.getDepositDate(),
                        members,
                        membershipFeeByGuest.getDescription()
                )
        );

        saveDeposits(deposits);
    }

    public void registerExtraFeeByMember(ExtraFee extraFee) {
        List<Deposit> deposits = Deposit.toEntityList(extraFee);
        saveDeposits(deposits);
    }

    private void saveDeposits(List<Deposit> deposits) {
        for (Deposit deposit : deposits) {
            depositRepository.save(deposit);
            updateBalance(deposit);
        }
    }

    private void updateBalance(Deposit deposit) {
        AccountBook latestAccountBook = accountBookRepository.findTopByOrderByIdDesc().orElseThrow(() -> new IllegalStateException("입출금 데이터가 없습니다."));
        log.debug("스탠스 가계부 최신 데이터 [{}]의 잔액 [{}]", latestAccountBook.getId(), latestAccountBook.getBalance());

        AccountBook updatedAccountBook = latestAccountBook.updateBalance(
                deposit.getId(),
                TransactionType.DEPOSIT,
                deposit.getDepositDate(),
                deposit.getDepositor(),
                deposit.getAmount()
        );

        accountBookRepository.save(updatedAccountBook);
    }
}
