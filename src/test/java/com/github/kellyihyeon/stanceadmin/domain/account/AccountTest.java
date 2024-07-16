package com.github.kellyihyeon.stanceadmin.domain.account;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class AccountTest {

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