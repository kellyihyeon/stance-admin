package com.github.kellyihyeon.stanceadmin.domain;

import lombok.Getter;

@Getter
public enum SearchingPeriodType {
    ONE_MONTH(1),
    TWO_MONTHS(2),
    THREE_MONTHS(3);

    private final int number;

    SearchingPeriodType(int number) {
        this.number = number;
    }
}
