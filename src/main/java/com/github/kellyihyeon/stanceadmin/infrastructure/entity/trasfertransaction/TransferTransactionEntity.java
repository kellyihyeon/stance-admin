package com.github.kellyihyeon.stanceadmin.infrastructure.entity.trasfertransaction;

import com.github.kellyihyeon.stanceadmin.domain.account.Bank;
import com.github.kellyihyeon.stanceadmin.domain.transfertransaction.ExpenseCategory;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Getter
@ToString
@Table(name = "transfer_transactions")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TransferTransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "expense_category", nullable = false)
    private ExpenseCategory expenseCategory;

    @Column(name = "recipient_name", nullable = false)
    private String recipientName;

    @Enumerated(EnumType.STRING)
    @Column(name = "bank", nullable = false)
    private Bank bank;

    @Column(name = "recipient_account_number", nullable = false)
    private String recipientAccountNumber;

    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    @Column(name = "expense_date", nullable = false)
    private LocalDate expenseDate;

    @Column(name = "description")
    private String description;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "creator_id", nullable = false)
    private Long creatorId;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "updater_id")
    private Long updaterId;

    private TransferTransactionEntity(ExpenseCategory expenseCategory, String recipientName, Bank bank, String recipientAccountNumber, BigDecimal amount, LocalDate expenseDate, String description, LocalDateTime createdAt, Long creatorId) {
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

    public static TransferTransactionEntity create(ExpenseCategory expenseCategory, String recipientName, Bank bank, String recipientAccountNumber, BigDecimal amount, LocalDate expenseDate, String description, LocalDateTime createdAt, Long creatorId) {
        Objects.requireNonNull(expenseCategory);
        Objects.requireNonNull(recipientName);
        Objects.requireNonNull(bank);
        Objects.requireNonNull(recipientAccountNumber);
        Objects.requireNonNull(amount);
        Objects.requireNonNull(expenseDate);
        Objects.requireNonNull(createdAt);
        Objects.requireNonNull(creatorId);

        return new TransferTransactionEntity(
                expenseCategory,
                recipientName,
                bank,
                recipientAccountNumber,
                amount,
                expenseDate,
                description,
                createdAt,
                creatorId
        );
    }
}
