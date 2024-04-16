package com.github.kellyihyeon.stanceadmin.domain.withdraw;

import com.github.kellyihyeon.stanceadmin.application.withdraw.dto.CardPaymentRequest;
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
    @Column(name = "withdraw_category", nullable = false)
    private WithdrawCategory withdrawCategory;

    @Enumerated(EnumType.STRING)
    @Column(name = "expense_category", nullable = false)
    private ExpenseCategory expenseCategory;

    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    @Column(name = "recipient_name")
    private String recipientName;

    @Enumerated(EnumType.STRING)
    @Column(name = "bank_name")
    private Bank bankName;

    @Column(name = "recipient_account_number")
    private String recipientAccountNumber;

    @Column(name = "expense_date", nullable = false)
    private LocalDate expenseDate;

    @Column(name = "card_payer")
    private String cardPayer;

    @Column(name = "card_usage_location")
    private String cardUsageLocation;

    @Column(name = "description")
    private String description;

    @Column(name = "creator_id", nullable = false)
    private Long creatorId;

    @Column(name = "created_date", nullable = false)
    private LocalDateTime createdDate;

    @Column(name = "updater_id")
    private Long updaterId;

    @Column(name = "updated_date")
    private LocalDateTime updatedDate;

    @Builder
    public Withdraw(
            Long memberId,
            WithdrawCategory withdrawCategory,
            ExpenseCategory expenseCategory,
            BigDecimal amount,
            LocalDate expenseDate,
            String description,
            Long creatorId,
            LocalDateTime createdDate
    ) {
        this.memberId = memberId;
        this.withdrawCategory = withdrawCategory;
        this.expenseCategory = expenseCategory;
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

    public Withdraw createCardPaymentWithdrawal(CardPaymentRequest cardPaymentRequest) {
        this.cardUsageLocation = cardPaymentRequest.cardUsageLocation();
        this.cardPayer = cardPaymentRequest.cardPayer();

        return this;
    }
}
