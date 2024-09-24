package com.github.kellyihyeon.stanceadmin.application.eventapplicantregistry.dto;

import com.github.kellyihyeon.stanceadmin.domain.eventapplicantregistry.DepositStatus;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@ToString
public class ApplicantRegistryData {
    private String eventDescription;

    private LocalDate dueDate;

    private String memberName;

    private Double amount;

    private DepositStatus depositStatus;

    private LocalDate depositDate;

    @QueryProjection
    public ApplicantRegistryData(String eventDescription, LocalDate dueDate, String memberName, Double amount, DepositStatus depositStatus, LocalDate depositDate) {
        this.eventDescription = eventDescription;
        this.dueDate = dueDate;
        this.memberName = memberName;
        this.amount = amount;
        this.depositStatus = depositStatus;
        this.depositDate = depositDate;
    }
}
