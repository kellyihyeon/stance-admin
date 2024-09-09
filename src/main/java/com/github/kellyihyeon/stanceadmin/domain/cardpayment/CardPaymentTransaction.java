package com.github.kellyihyeon.stanceadmin.domain.cardpayment;

import com.github.kellyihyeon.stanceadmin.domain.transfertransaction.ExpenseCategory;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CardPaymentTransaction {

    private Long id;

    private Long cardHolderId;

    private ExpenseCategory expenseCategory;

    private String cardUsageLocation;

    private BigDecimal amount;

    private LocalDate expenseDate;

    private String description;

    private Long creatorId;

    private LocalDateTime createdAt;

    private Long updaterId;

    private LocalDateTime updatedAt;

    private CardPaymentTransaction(Long id, Long cardHolderId, ExpenseCategory expenseCategory, String cardUsageLocation, BigDecimal amount, LocalDate expenseDate, String description, Long creatorId, LocalDateTime createdAt) {
        this.id = id;
        this.cardHolderId = cardHolderId;
        this.expenseCategory = expenseCategory;
        this.cardUsageLocation = cardUsageLocation;
        this.amount = amount;
        this.expenseDate = expenseDate;
        this.description = description;
        this.creatorId = creatorId;
        this.createdAt = createdAt;
    }

    private CardPaymentTransaction(Long cardHolderId, ExpenseCategory expenseCategory, String cardUsageLocation, BigDecimal amount, LocalDate expenseDate, String description, Long creatorId, LocalDateTime createdAt) {
        this.cardHolderId = cardHolderId;
        this.expenseCategory = expenseCategory;
        this.cardUsageLocation = cardUsageLocation;
        this.amount = amount;
        this.expenseDate = expenseDate;
        this.description = description;
        this.creatorId = creatorId;
        this.createdAt = createdAt;
    }

    public static CardPaymentTransaction create(Long cardHolderId, ExpenseCategory expenseCategory, String cardUsageLocation, BigDecimal amount, LocalDate expenseDate, String description, Long loggedInId, LocalDateTime now) {
        Objects.requireNonNull(cardHolderId);
        Objects.requireNonNull(expenseCategory);
        Objects.requireNonNull(cardUsageLocation);
        Objects.requireNonNull(amount);
        Objects.requireNonNull(expenseDate);
        Objects.requireNonNull(loggedInId);
        Objects.requireNonNull(now);

        return new CardPaymentTransaction(
                cardHolderId,
                expenseCategory,
                cardUsageLocation,
                amount,
                expenseDate,
                description,
                loggedInId,
                now
        );
    }

    public static CardPaymentTransaction createWithId(Long id, Long cardHolderId, ExpenseCategory expenseCategory, String cardUsageLocation, BigDecimal amount, LocalDate expenseDate, String description, Long creatorId, LocalDateTime createdAt) {
        Objects.requireNonNull(id);
        Objects.requireNonNull(cardHolderId);
        Objects.requireNonNull(expenseCategory);
        Objects.requireNonNull(cardUsageLocation);
        Objects.requireNonNull(amount);
        Objects.requireNonNull(expenseDate);
        Objects.requireNonNull(creatorId);
        Objects.requireNonNull(createdAt);

        return new CardPaymentTransaction(
                id,
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
