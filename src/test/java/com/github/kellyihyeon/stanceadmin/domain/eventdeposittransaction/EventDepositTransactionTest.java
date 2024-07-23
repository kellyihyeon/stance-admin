package com.github.kellyihyeon.stanceadmin.domain.eventdeposittransaction;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class EventDepositTransactionTest {

    @Test
    void create() {
        assertThatThrownBy(
                () -> EventDepositTransactionBuilder.builder()
                        .eventId(null)
                        .build())
                .isInstanceOf(NullPointerException.class).hasMessageContaining("eventId 는 null 이어선 안됩니다.");

        assertThatThrownBy(
                () -> EventDepositTransactionBuilder.builder()
                        .applicantId(null)
                        .build())
                .isInstanceOf(NullPointerException.class).hasMessageContaining("applicantId 는 null 이어선 안됩니다.");

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
