package com.github.kellyihyeon.stanceadmin.domain.eventdeposit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class EventDepositTransactionTest {

    @Test
    void create() {
        EventDepositTransaction eventDepositTransaction = EventDepositTransactionBuilder.builder()
                .eventId(1L)
                .applicantId(2L)
                .amount((double) 70000)
                .depositDate(LocalDate.now())
                .creatorId(999L)
                .createdAt(LocalDateTime.now())
                .build();

        Assertions.assertEquals(1L, eventDepositTransaction.getEventId());
    }

    @Test
    @DisplayName("객체 생성 시 필수값이 없으면 NullPointerException 이 발생한다.")
    void 이벤트_입금_내역_객체_생성_시_필수값이_없는_경우() {
        assertThatThrownBy(
                () -> EventDepositTransactionBuilder.builder()
                        .eventId(null)
                        .build())
                .isInstanceOf(NullPointerException.class).hasMessageContaining("eventId 는 null 이어선 안됩니다.");

        assertThatThrownBy(
                () -> EventDepositTransactionBuilder.builder()
                        .applicantId(null)
                        .build())
                .isInstanceOf(NullPointerException.class).hasMessageContaining("depositorId 는 null 이어선 안됩니다.");

        assertThatThrownBy(
                () -> EventDepositTransactionBuilder.builder()
                        .amount(null)
                        .build())
                .isInstanceOf(NullPointerException.class).hasMessageContaining("amount 는 null 이어선 안됩니다.");

        assertThatThrownBy(
                () -> EventDepositTransactionBuilder.builder()
                        .depositDate(null)
                        .build())
                .isInstanceOf(NullPointerException.class).hasMessageContaining("depositDate 는 null 이어선 안됩니다.");

        assertThatThrownBy(
                () -> EventDepositTransactionBuilder.builder()
                        .creatorId(null)
                        .build())
                .isInstanceOf(NullPointerException.class).hasMessageContaining("creatorId 는 null 이어선 안됩니다.");

        assertThatThrownBy(
                () -> EventDepositTransactionBuilder.builder()
                        .createdAt(null)
                        .build())
                .isInstanceOf(NullPointerException.class).hasMessageContaining("createdAt 은 null 이어선 안됩니다.");
    }
}
