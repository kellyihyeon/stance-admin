package com.github.kellyihyeon.stanceadmin.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BankDepositTransactionTest {

    @Test
    @DisplayName("캐시백과 이자 입금 내역을 만들고 입금 타입이 일치하는지 확인한다.")
    void 캐시백과_이자_입금_내역_만들기() {
        BankDepositTransaction bankDepositTransaction = new BankDepositTransaction();
        BankDepositTransaction cashback = bankDepositTransaction.depositCashback();
        BankDepositTransaction interest = bankDepositTransaction.depositInterest();

        assertThat(cashback.getDepositType()).isEqualTo("CASHBACK");
        assertThat(interest.getDepositType()).isEqualTo("INTEREST");
    }
}
