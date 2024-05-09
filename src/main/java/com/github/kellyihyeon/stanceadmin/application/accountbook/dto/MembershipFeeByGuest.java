package com.github.kellyihyeon.stanceadmin.application.accountbook.dto;

import lombok.Getter;

import java.math.BigDecimal;
import java.time.Month;
import java.time.Year;
import java.util.List;

@Getter
public class MembershipFeeByGuest {

    private BigDecimal amount;

    private Year dueYear;

    private Month dueMonth;

    private String depositDate;

    private List<String> names;

    private String description;

}
