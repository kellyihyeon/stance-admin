package com.github.kellyihyeon.stanceadmin.application.accountbook.dto;

import lombok.Getter;

import java.time.Month;
import java.util.List;

@Getter
public class MembershipFeeByGuest {

    private Long amount;

    private Month dueMonth;

    private String depositDate;

    private List<String> names;

    private String description;

}
