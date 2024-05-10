package com.github.kellyihyeon.stanceadmin.application.accountbook;

import com.github.kellyihyeon.stanceadmin.application.accountbook.dto.*;
import com.github.kellyihyeon.stanceadmin.domain.SearchingPeriodType;
import com.github.kellyihyeon.stanceadmin.domain.accountbook.AccountBook;
import com.github.kellyihyeon.stanceadmin.infrastructure.repository.accountbook.AccountBookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

@Service
@RequiredArgsConstructor
public class AccountBookService {

    private final AccountBookRepository accountBookRepository;


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

    public BalanceResponse retrieveLatestBalance() {
        AccountBook latestAccountBook = accountBookRepository.findTopByOrderByIdDesc().orElseThrow(() -> new IllegalStateException("입출금 데이터가 없습니다."));
        return new BalanceResponse(latestAccountBook.getBalance());
    }
}
