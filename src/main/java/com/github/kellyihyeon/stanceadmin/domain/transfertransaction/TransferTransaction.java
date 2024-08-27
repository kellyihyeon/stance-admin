package com.github.kellyihyeon.stanceadmin.domain.transfertransaction;

import com.github.kellyihyeon.stanceadmin.domain.account.Bank;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TransferTransaction {

    private Long id;

    private ExpenseCategory expenseCategory;

    private String recipientName;

    private Bank bank;

    private String recipientAccountNumber;

    private BigDecimal amount;

    private LocalDate expenseDate;

    private String description;

    private LocalDateTime createdAt;

    private Long creatorId;

    private LocalDateTime updatedAt;

    private Long updaterId;

    private TransferTransaction(ExpenseCategory expenseCategory, String recipientName, Bank bank, String recipientAccountNumber, BigDecimal amount, LocalDate expenseDate, String description, Long creatorId, LocalDateTime createdAt) {
        this.expenseCategory = expenseCategory;
        this.recipientName = recipientName;
        this.bank = bank;
        this.recipientAccountNumber = recipientAccountNumber;
        this.amount = amount;
        this.expenseDate = expenseDate;
        this.description = description;
        this.createdAt = createdAt;
        this.creatorId = creatorId;
    }


    public static TransferTransaction createWithoutId(ExpenseCategory expenseCategory, String recipientName, Bank bank, String recipientAccountNumber, BigDecimal amount, LocalDate expenseDate, String description, Long creatorId, LocalDateTime createdAt) {
        Objects.requireNonNull(expenseCategory);
        Objects.requireNonNull(recipientName);
        Objects.requireNonNull(bank);
        Objects.requireNonNull(recipientAccountNumber);
        Objects.requireNonNull(amount);
        Objects.requireNonNull(expenseDate);
        Objects.requireNonNull(creatorId);
        Objects.requireNonNull(createdAt);

        return new TransferTransaction(
                expenseCategory,
                recipientName,
                bank,
                recipientAccountNumber,
                amount,
                expenseDate,
                description,
                creatorId,
                createdAt
        );
    }
}
