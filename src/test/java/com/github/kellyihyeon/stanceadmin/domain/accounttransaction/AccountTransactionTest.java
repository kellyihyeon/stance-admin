package com.github.kellyihyeon.stanceadmin.domain.accounttransaction;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AccountTransactionTest {

    @Test
    @DisplayName("입금인 경우 최신 잔액에 입금액을 더한다.")
    void 입금_내역의_경우_잔액_계산하기() {
        Double latestBalance = (double) 100000;
        AccountTransaction accountTransaction = AccountTransactionBuilder.builder()
                .amount((double) 70000)
                .build();

        Double actualBalance = accountTransaction.calculateBalance(latestBalance, TransactionType.DEPOSIT);
        Double expectedBalance = (double) 170000;

        assertEquals(expectedBalance, actualBalance);
    }

    @Test
    @DisplayName("accountId 가 null 이면 NullPointerException이 발생한다.")
    void accountId가_null인_경우() {
        assertThrows(NullPointerException.class,
                () -> AccountTransactionBuilder.builder()
                        .accountId(null)
                        .build());
    }

    @Test
    @DisplayName("transactionType 이 null 이면 NullPointerException이 발생한다.")
    void transactionType이_null인_경우() {
        assertThrows(NullPointerException.class,
                () -> AccountTransactionBuilder.builder()
                        .transactionType(null)
                        .build());
    }

    @Test
    @DisplayName("transactionId 가 null 이면 NullPointerException이 발생한다.")
    void transactionId가_null인_경우() {
        assertThrows(NullPointerException.class,
                () -> AccountTransactionBuilder.builder()
                        .transactionId(null)
                        .build());
    }

    @Test
    @DisplayName("transactionSubType 이 null 이면 NullPointerException이 발생한다.")
    void transactionSubType이_null인_경우() {
        assertThrows(NullPointerException.class,
                () -> AccountTransactionBuilder.builder()
                        .transactionSubType(null)
                        .build());
    }

    @Test
    @DisplayName("createdAt 이 null 이면 NullPointerException이 발생한다.")
    void createdAt이_null인_경우() {
        assertThrows(NullPointerException.class,
                () -> AccountTransactionBuilder.builder()
                        .createdAt(null)
                        .build());
    }

    @Test
    @DisplayName("creatorId 가 null 이면 NullPointerException이 발생한다.")
    void creatorId가_null인_경우() {
        assertThrows(NullPointerException.class,
                () -> AccountTransactionBuilder.builder()
                        .creatorId(null)
                        .build());
    }
}