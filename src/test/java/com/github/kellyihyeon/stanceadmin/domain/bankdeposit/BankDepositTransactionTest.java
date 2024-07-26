package com.github.kellyihyeon.stanceadmin.domain.bankdeposit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

public class BankDepositTransactionTest {

    @Test
    @DisplayName("캐시백과 이자 입금 내역을 만들고 입금 타입이 일치하는지 확인한다.")
    void 캐시백과_이자_입금_내역_만들기() {
        Long id = 5L;
        Long depositorId = 4L;
        double amount = 1300;
        LocalDate depositDate = LocalDate.now();

        BankDepositTransaction bankDepositTransaction = new BankDepositTransaction();
        BankDepositTransaction cashback = bankDepositTransaction.depositCashback(id, depositorId, amount, depositDate);
        BankDepositTransaction interest = bankDepositTransaction.depositInterest(id, depositorId, amount, depositDate);

        assertThat(cashback.getDepositType()).isEqualTo(DepositType.CASHBACK);
        assertThat(interest.getDepositType()).isEqualTo(DepositType.INTEREST);
    }
}
