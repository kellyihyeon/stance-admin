package com.github.kellyihyeon.stanceadmin.application.accountbook;

import com.github.kellyihyeon.stanceadmin.application.accountbook.dto.*;
import com.github.kellyihyeon.stanceadmin.application.deposit.dto.CashFormByBank;
import com.github.kellyihyeon.stanceadmin.application.deposit.dto.ExtraFee;
import com.github.kellyihyeon.stanceadmin.application.member.dto.MemberIdAndName;
import com.github.kellyihyeon.stanceadmin.application.withdraw.dto.CardPaymentRequest;
import com.github.kellyihyeon.stanceadmin.application.withdraw.dto.TransferRequest;
import com.github.kellyihyeon.stanceadmin.application.withdraw.dto.WithdrawRequest;
import com.github.kellyihyeon.stanceadmin.domain.SearchingPeriodType;
import com.github.kellyihyeon.stanceadmin.domain.accountbook.AccountBook;
import com.github.kellyihyeon.stanceadmin.domain.accountbook.TransactionType;
import com.github.kellyihyeon.stanceadmin.domain.deposit.Deposit;
import com.github.kellyihyeon.stanceadmin.domain.member.MemberType;
import com.github.kellyihyeon.stanceadmin.domain.member.MembershipFeeType;
import com.github.kellyihyeon.stanceadmin.domain.withdraw.Withdraw;
import com.github.kellyihyeon.stanceadmin.infrastructure.repository.accountbook.AccountBookRepository;
import com.github.kellyihyeon.stanceadmin.infrastructure.repository.deposit.DepositRepository;
import com.github.kellyihyeon.stanceadmin.infrastructure.repository.withdraw.WithdrawRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountBookService {

    private final AccountBookRepository accountBookRepository;
    private final DepositRepository depositRepository;
    private final WithdrawRepository withdrawRepository;


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

    // TODO. Refactoring 대상
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

    public void registerCashByBank(CashFormByBank cashFormByBank) {
        Deposit deposit = cashFormByBank.toEntity();
        depositRepository.save(deposit);
        updateBalance(deposit);
    }

    @Transactional
    public void processTransferWithdrawal(TransferRequest transferRequest) {
        Withdraw withdraw = createWithdraw(transferRequest);
        withdrawRepository.save(withdraw);

        AccountBook accountBook = updateAccountBookBalance(new TransactionRecord(
                TransactionType.WITHDRAW,
                withdraw.getId(),
                transferRequest.transactionDate(),
                transferRequest.recipientName(),
                transferRequest.amount()));
        accountBookRepository.save(accountBook);
    }

    private AccountBook updateAccountBookBalance(TransactionRecord transactionRecord) {
        AccountBook latestAccountBook = accountBookRepository.findTopByOrderByIdDesc().orElseThrow(() -> new IllegalStateException("입출금 데이터가 없습니다."));
        log.debug("스탠스 가계부 최신 데이터 [{}]의 잔액 [{}]", latestAccountBook.getId(), latestAccountBook.getBalance());

        return latestAccountBook.updateBalance(transactionRecord);
    }

    private Withdraw createWithdraw(WithdrawRequest request) {
        Withdraw withdraw = Withdraw.builder()
                .memberId(1L)
                .withdrawCategory(request.getWithdrawCategory())
                .expenseCategory(request.getExpenseCategory())
                .amount(request.getAmount())
                .expenseDate(request.getExpenseDate())
                .description(request.getDescription())
                .creatorId(1L)
                .createdDate(LocalDateTime.now())
                .build();

        if (request instanceof TransferRequest transferRequest) {
            return withdraw.createTransferWithdrawal(transferRequest);
        }

        if (request instanceof CardPaymentRequest cardPaymentRequest) {
            return withdraw.createCardPaymentWithdrawal(cardPaymentRequest);
        }

        return withdraw;
    }

    @Transactional
    public void processCardPaymentWithdrawal(CardPaymentRequest cardPaymentRequest) {
        Withdraw withdraw = createWithdraw(cardPaymentRequest);
        withdrawRepository.save(withdraw);

        AccountBook accountBook = updateAccountBookBalance(new TransactionRecord(
                TransactionType.WITHDRAW,
                withdraw.getId(),
                cardPaymentRequest.expenseDate(),
                cardPaymentRequest.cardUsageLocation(),
                cardPaymentRequest.amount()
        ));
        accountBookRepository.save(accountBook);
    }

    public List<AccountBookResponse> retrieveAccountBooksByPeriod(SearchingPeriodType period) {
        LocalDate today = LocalDate.now();

        return accountBookRepository.
                findAccountBookByTransactionDateBetweenOrderByTransactionDateDesc(
                        today.minusMonths(period.getNumber()),
                        today
                );

    }

    public List<FinancialTransactionResponse> retrieveAccountBooksByYear(int year) {
        LocalDate firstDate = LocalDate.of(year, 1, 1);
        LocalDate december = LocalDate.of(year, 12, 1);
        LocalDate lastDate = december.withDayOfMonth(december.lengthOfMonth());

        List<AccountBook> accountBooks = accountBookRepository.findByTransactionDateBetween(firstDate, lastDate);

        Map<Integer, MonthlyTransaction> monthlyTransactions = calculateMonthlyTransaction(accountBooks);
        return summarizeMonthlyTransactions(monthlyTransactions);
    }

    private List<FinancialTransactionResponse> summarizeMonthlyTransactions(Map<Integer, MonthlyTransaction> monthlyTransactions) {
        List<FinancialTransactionResponse> financialTransactions = new ArrayList<>();

        for (Map.Entry<Integer, MonthlyTransaction> entry : monthlyTransactions.entrySet()) {
            Integer month = entry.getKey();
            MonthlyTransaction monthlyTransaction = entry.getValue();

            financialTransactions.add(new FinancialTransactionResponse(month, monthlyTransaction));
        }

        return financialTransactions;
    }

    private Map<Integer, MonthlyTransaction> calculateMonthlyTransaction(List<AccountBook> accountBooks) {
        Map<Integer, MonthlyTransaction> monthlyTransactions = new HashMap<>();
        for (AccountBook accountBook : accountBooks) {
            int month = accountBook.getTransactionDate().getMonthValue();
            MonthlyTransaction monthlyTransaction = monthlyTransactions.getOrDefault(month, new MonthlyTransaction(BigDecimal.ZERO, BigDecimal.ZERO));

            if (accountBook.isDeposit()) {
                monthlyTransaction.plusDepositAmount(accountBook.getAmount());
            }

            if (accountBook.isWithdraw()) {
                monthlyTransaction.plusWithdrawalAmount(accountBook.getAmount());
            }

            monthlyTransactions.put(month, monthlyTransaction);
        }

        return monthlyTransactions;
    }
}
