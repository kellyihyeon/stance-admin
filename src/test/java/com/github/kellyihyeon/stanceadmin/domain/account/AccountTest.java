package com.github.kellyihyeon.stanceadmin.domain.account;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AccountTest {

    @Test
    void 유효한_계좌ID로_잔액을_업데이트_하는_경우() {
        Account sut = AccountBuilder.builder()
                .id(99L)
                .balance((double) 500)
                .build();

        Long validAccountId = 99L;
        double balance = 100000;
        sut.updateBalance(validAccountId, balance);

        assertEquals(100000, sut.getBalance());
    }

    @Test
    @DisplayName("계좌의 id가 일치하지 않으면 IllegalArgumentException 이 발생한다.")
    void 유효하지_않은_계좌id로_잔액을_업데이트_하는_경우() {
        Account sut = AccountBuilder.builder()
                .id(99L)
                .build();

        Long invalidAccountId = 3L;
        double balance = 100000;

        assertThrows(
                IllegalArgumentException.class,
                () -> sut.updateBalance(invalidAccountId, balance)
        );
    }

    @Test
    @DisplayName("기본 계좌 여부가 null이면 IllegalArgumentException이 발생한다.")
    void 기본_계좌_여부가_null인_경우() {
        assertThrows(
                IllegalArgumentException.class,
                () -> AccountBuilder.builder()
                        .isDefault(null)
                        .build());
    }

    @Test
    @DisplayName("계좌 별칭이 null이면 IllegalArgumentException이 발생한다.")
    void 계좌_별칭이_null인_경우() {
        assertThrows(
                IllegalArgumentException.class,
                () -> AccountBuilder.builder()
                        .accountNickname(null)
                        .build());
    }

    @Test
    @DisplayName("잔액이 null이면 IllegalArgumentException이 발생한다.")
    void 잔액이_null인_경우() {
        assertThrows(
                IllegalArgumentException.class,
                () -> AccountBuilder.builder()
                        .balance(null)
                        .build());
    }

    @Test
    @DisplayName("계좌 소유주가 null이면 IllegalArgumentException이 발생한다.")
    void 계좌_소유주가_null인_경우() {
        assertThrows(
                IllegalArgumentException.class,
                () -> AccountBuilder.builder()
                        .accountHolder(null)
                        .build());
    }

    @Test
    @DisplayName("계좌번호가 null이면 IllegalArgumentException이 발생한다.")
    void 계좌번호가_null인_경우() {
        assertThrows(
                IllegalArgumentException.class,
                () -> AccountBuilder.builder()
                        .accountNumber(null)
                        .build());
    }

    @Test
    @DisplayName("은행명이 null이면 IllegalArgumentException이 발생한다.")
    void 은행명이_null인_경우() {
        assertThrows(
                IllegalArgumentException.class,
                () -> AccountBuilder.builder()
                        .bank(null)
                        .build());
    }

    @Test
    @DisplayName("사용일이 null이면 IllegalArgumentException이 발생한다.")
    void 사용일이_null인_경우() {
        assertThrows(
                IllegalArgumentException.class,
                () -> AccountBuilder.builder()
                        .usageStartDate(null)
                        .build());
    }

    @Test
    @DisplayName("계좌 상태가 null이면 IllegalArgumentException이 발생한다.")
    void 계좌_상태가_null인_경우() {
        assertThrows(
                IllegalArgumentException.class,
                () -> AccountBuilder.builder()
                        .accountStatus(null)
                        .build());
    }

    @Test
    @DisplayName("데이터 생성일이 null이면 IllegalArgumentException이 발생한다.")
    void 데이터_생성일이_null인_경우() {
        assertThrows(
                IllegalArgumentException.class,
                () -> AccountBuilder.builder()
                        .createdAt(null)
                        .build());
    }

    @Test
    @DisplayName("데이터 생성자가 null이면 IllegalArgumentException이 발생한다.")
    void 데이터_생성자가_null인_경우() {
        assertThrows(
                IllegalArgumentException.class,
                () -> AccountBuilder.builder()
                        .creatorId(null)
                        .build());
    }

}