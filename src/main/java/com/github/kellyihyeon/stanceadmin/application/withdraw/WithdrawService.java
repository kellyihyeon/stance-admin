package com.github.kellyihyeon.stanceadmin.application.withdraw;

import com.github.kellyihyeon.stanceadmin.application.accountbook.dto.TransactionRecord;
import com.github.kellyihyeon.stanceadmin.application.withdraw.dto.*;
import com.github.kellyihyeon.stanceadmin.domain.accountbook.AccountBook;
import com.github.kellyihyeon.stanceadmin.domain.accountbook.TransactionType;
import com.github.kellyihyeon.stanceadmin.domain.withdraw.Withdraw;
import com.github.kellyihyeon.stanceadmin.infrastructure.repository.accountbook.AccountBookRepository;
import com.github.kellyihyeon.stanceadmin.infrastructure.repository.withdraw.WithdrawRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class WithdrawService {

    private final WithdrawRepository withdrawRepository;
    private final AccountBookRepository accountBookRepository;


    public TopExpenseCategoriesResponse getTopWithdrawsByCategoryTotalSum(Year year, Month month) {
        final int FIRST_DAY = 1;
        LocalDate startDate = LocalDate.of(year.getValue(), month.getValue(), FIRST_DAY);
        LocalDate endDate = startDate.withDayOfMonth(startDate.lengthOfMonth());

        final int TOP_CATEGORY_LIMIT_COUNT = 3;
        List<ExpenseCategoryTotalSum> expenseCategoryTotalSums = withdrawRepository.findTopCategoryTotalSumByExpenseDateBetween(
                startDate,
                endDate,
                TOP_CATEGORY_LIMIT_COUNT
        );


        List<ExpenseCategoryTotalSum> history = calculateRankings(expenseCategoryTotalSums);

        return TopExpenseCategoriesResponse
                .builder()
                .year(year)
                .month(month.getValue())
                .total(calculateTotalSumAllCategories(history))
                .history(history)
                .build();
    }

    private BigDecimal calculateTotalSumAllCategories(List<ExpenseCategoryTotalSum> history) {
        BigDecimal total = BigDecimal.ZERO;

        for (ExpenseCategoryTotalSum expenseCategoryTotalSum : history) {
            total = total.add(expenseCategoryTotalSum.getTotal());
        }

        return total;
    }

    private List<ExpenseCategoryTotalSum> calculateRankings(List<ExpenseCategoryTotalSum> expenseCategoryTotalSums) {
        List<ExpenseCategoryTotalSum> history = new ArrayList<>();

        int rank = 1;
        for (ExpenseCategoryTotalSum expenseCategoryTotalSum : expenseCategoryTotalSums) {
            history.add(ExpenseCategoryTotalSum.builder()
                    .rank(rank++)
                    .expenseCategory(expenseCategoryTotalSum.getExpenseCategory())
                    .total(expenseCategoryTotalSum.getTotal())
                    .build());
        }

        return history;
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

    private AccountBook updateAccountBookBalance(TransactionRecord transactionRecord) {
        AccountBook latestAccountBook = accountBookRepository.findTopByOrderByIdDesc().orElseThrow(() -> new IllegalStateException("입출금 데이터가 없습니다."));
        log.debug("스탠스 가계부 최신 데이터 [{}]의 잔액 [{}]", latestAccountBook.getId(), latestAccountBook.getBalance());

        return latestAccountBook.updateBalance(transactionRecord);
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
}
