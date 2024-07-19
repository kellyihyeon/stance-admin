package com.github.kellyihyeon.stanceadmin.domain.accounttransaction;

import com.github.kellyihyeon.stanceadmin.domain.member.MemberType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MembershipFeeDepositTransaction {

    private Long id;

    private Long depositorId;

    private LocalDate depositDate;

    private Double amount;

    private LocalDate dueDate;

    private MemberType memberType;

    private String description;

    private LocalDateTime createdAt;

    private Long creatorId;

    private LocalDateTime updatedAt;

    private Long updaterId;

    public MembershipFeeDepositTransaction(Long depositorId, LocalDate depositDate, Double amount, LocalDate dueDate, MemberType memberType, String description, LocalDateTime createdAt, Long creatorId) {
        this.depositorId = depositorId;
        this.depositDate = depositDate;
        this.amount = amount;
        this.dueDate = dueDate;
        this.memberType = memberType;
        this.description = description;
        this.createdAt = createdAt;
        this.creatorId = creatorId;
    }
}
