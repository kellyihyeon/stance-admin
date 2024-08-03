package com.github.kellyihyeon.stanceadmin.domain.eventapplicantregistry;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;


class EventApplicantRegistryTest {

    private EventApplicantRegistry sut;

    @BeforeEach
    void setUp() {
        sut = EventApplicantRegistry.create(1L,
                1L,
                "초보자 대회 교류전"
        );
    }

    @Test
    @DisplayName("DepositStatus 가 입금완료에서 미입금 상태로 변경되면 에러가 발생한다.")
    void depositStatus가_입금완료에서_미입금_상태로_변경() {
        sut.markAsPaid();
        assertThat(sut.getDepositStatus()).isEqualTo(DepositStatus.COMPLETED);
        assertThatThrownBy(() -> sut.markAsUnpaid()).isInstanceOf(IllegalArgumentException.class).hasMessageContaining("이미 입금완료 처리된 명단은 미입금 처리 할 수 없습니다.");
    }

    @Test
    @DisplayName("객체를 생성하면 DepositStatus 는 미입금 상태이다.")
    void createEventApplicantRegistry() {
        assertEquals(DepositStatus.NOT_COMPLETED, sut.getDepositStatus());
    }

}