package com.github.kellyihyeon.stanceadmin.domain.withdraw;

import com.github.kellyihyeon.stanceadmin.application.withdraw.dto.TransferRequest;
import com.github.kellyihyeon.stanceadmin.domain.accountbook.Bank;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@ToString
@Table(name = "withdraws")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Withdraw {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // TODO. Member 도메인 만들고 관계 설정
    private Long memberId;

    @Enumerated(EnumType.STRING)
    private WithdrawCategory withdrawCategory;

    @Enumerated(EnumType.STRING)
    private ExpenseCategory expenseCategory;

    private String spender;

    private BigDecimal amount;

    private String recipientName;

    @Enumerated(EnumType.STRING)
    private Bank bankName;

    private String recipientAccountNumber;

    private LocalDate expenseDate;

    private String cardUsageLocation;

    private String description;

    private Long creatorId;

    private LocalDateTime createdDate;

    private Long updaterId;

    private LocalDateTime updatedDate;

    @Builder
    public Withdraw(
            Long memberId,
            WithdrawCategory withdrawCategory,
            ExpenseCategory expenseCategory,
            String spender,
            BigDecimal amount,
            LocalDate expenseDate,
            String description,
            Long creatorId,
            LocalDateTime createdDate
    ) {
        this.memberId = memberId;
        this.withdrawCategory = withdrawCategory;
        this.expenseCategory = expenseCategory;
        this.spender = spender;
        this.amount = amount;
        this.expenseDate = expenseDate;
        this.description = description;
        this.creatorId = creatorId;
        this.createdDate = createdDate;
    }

    public Withdraw createTransferWithdrawal(
            TransferRequest request
    ) {
        this.recipientName = request.recipientName();
        this.bankName = request.bankName();
        this.recipientAccountNumber = request.recipientAccountNumber();

        return this;
    }
}
