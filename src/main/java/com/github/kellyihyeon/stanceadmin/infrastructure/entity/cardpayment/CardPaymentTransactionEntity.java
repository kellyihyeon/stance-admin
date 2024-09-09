package com.github.kellyihyeon.stanceadmin.infrastructure.entity.cardpayment;

import com.github.kellyihyeon.stanceadmin.domain.transfertransaction.ExpenseCategory;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Getter
@Table(name = "card_payment_transactions")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CardPaymentTransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "card_holder_id", nullable = false)
    private Long cardHolderId;

    @Enumerated(EnumType.STRING)
    @Column(name = "expense_category", nullable = false)
    private ExpenseCategory expenseCategory;

    @Column(name = "card_usage_location", nullable = false)
    private String cardUsageLocation;

    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    @Column(name = "expense_date", nullable = false)
    private LocalDate expenseDate;

    @Column(name = "description")
    private String description;

    @Column(name = "creator_id", nullable = false)
    private Long creatorId;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updater_id")
    private Long updaterId;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    private CardPaymentTransactionEntity(Long cardHolderId, ExpenseCategory expenseCategory, String cardUsageLocation, BigDecimal amount, LocalDate expenseDate, String description, Long creatorId, LocalDateTime createdAt) {
        this.cardHolderId = cardHolderId;
        this.expenseCategory = expenseCategory;
        this.cardUsageLocation = cardUsageLocation;
        this.amount = amount;
        this.expenseDate = expenseDate;
        this.description = description;
        this.creatorId = creatorId;
        this.createdAt = createdAt;
    }

    public static CardPaymentTransactionEntity create(Long cardHolderId, ExpenseCategory expenseCategory, String cardUsageLocation, BigDecimal amount, LocalDate expenseDate, String description, Long creatorId, LocalDateTime createdAt) {
        Objects.requireNonNull(cardHolderId);
        Objects.requireNonNull(expenseCategory);
        Objects.requireNonNull(cardUsageLocation);
        Objects.requireNonNull(amount);
        Objects.requireNonNull(expenseDate);
        Objects.requireNonNull(creatorId);
        Objects.requireNonNull(createdAt);

        return new CardPaymentTransactionEntity(
                cardHolderId,
                expenseCategory,
                cardUsageLocation,
                amount,
                expenseDate,
                description,
                creatorId,
                createdAt
        );
    }
}

