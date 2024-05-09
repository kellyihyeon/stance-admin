package com.github.kellyihyeon.stanceadmin.application.deposit.dto;

import com.github.kellyihyeon.stanceadmin.domain.deposit.DepositCategory;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;

@Getter
@ToString
@NoArgsConstructor
public class Depositor {

    private Long id;
    private Long memberId;
    private String memberName;
    private DepositCategory category;
    private BigDecimal amount;
    private Year dueYear;
    private Month dueMonth;
    private LocalDate depositDate;

    @QueryProjection
    public Depositor(Long id, Long memberId, String memberName, DepositCategory category, BigDecimal amount, Year dueYear, Month dueMonth, LocalDate depositDate) {
        this.id = id;
        this.memberId = memberId;
        this.memberName = memberName;
        this.category = category;
        this.amount = amount;
        this.dueYear = dueYear;
        this.dueMonth = dueMonth;
        this.depositDate = depositDate;
    }
}
