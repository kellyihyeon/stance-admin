package com.github.kellyihyeon.stanceadmin.domain.accounttransaction;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AccountTransactionTest {

    @Test
    @DisplayName("트랜잭션을 다루는 객체를 생성한다.")
    void 트랜잭션_객체_생성() {
        TransactionIdentity depositTransaction = new TransactionIdentity().create(5L, TransactionType.DEPOSIT, TransactionSubType.EVENT);

        assertEquals(5L, depositTransaction.getTransactionId());
        assertEquals(TransactionType.DEPOSIT, depositTransaction.getType());

        TransactionIdentity withdrawalTransaction = new TransactionIdentity().create(6L, TransactionType.WITHDRAW, TransactionSubType.TRANSFER);

        assertEquals(6L, withdrawalTransaction.getTransactionId());
        assertEquals(TransactionType.WITHDRAW, withdrawalTransaction.getType());
    }

    @Test
    @DisplayName("출금 내역인 경우 최신 잔액에서 출금액을 빼서 계산하다.")
    void 출금_내역인_경우_잔액() {
        Double latestBalance = (double) 100000;
        AccountTransaction accountTransaction = AccountTransactionBuilder.builder()
                .amount((double) 70000)
                .build();

        BigDecimal subtractedBalance = accountTransaction.subtractAmountFromBalance(BigDecimal.valueOf(latestBalance));
        double actualBalance = subtractedBalance.doubleValue();
        Double expectedBalance = (double) 30000;

        assertEquals(expectedBalance, actualBalance);

    }

    @Test
    @DisplayName("입금 내역인 경우 최신 잔액에서 입금액을 더해서 계산한다.")
    void 입금_내역인_경우_잔액() {
        Double latestBalance = (double) 100000;
        AccountTransaction accountTransaction = AccountTransactionBuilder.builder()
                .amount((double) 70000)
                .build();

        BigDecimal addedBalance = accountTransaction.addAmountToBalance(BigDecimal.valueOf(latestBalance));
        double actualBalance = addedBalance.doubleValue();
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