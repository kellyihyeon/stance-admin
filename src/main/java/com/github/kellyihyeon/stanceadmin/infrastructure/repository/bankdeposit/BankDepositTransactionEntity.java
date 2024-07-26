package com.github.kellyihyeon.stanceadmin.infrastructure.repository.bankdeposit;

import com.github.kellyihyeon.stanceadmin.domain.bankdeposit.DepositType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "bank_deposit_transactions")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BankDepositTransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "deposit_type", nullable = false)
    private DepositType depositType;

    @Column(name = "deposit_source", nullable = false)
    private String depositSource;

    @Column(name = "amount", nullable = false)
    private Double amount;

    @Column(name = "deposit_date", nullable = false)
    private LocalDate depositDate;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "creator_id", nullable = false)
    private Long creatorId;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "updater_id")
    private Long updaterId;


    private BankDepositTransactionEntity(DepositType depositType, String depositSource, Double amount, LocalDate depositDate, LocalDateTime createdAt, Long creatorId) {
        this.depositType = depositType;
        this.depositSource = depositSource;
        this.amount = amount;
        this.depositDate = depositDate;
        this.createdAt = createdAt;
        this.creatorId = creatorId;
    }

    public static BankDepositTransactionEntity create(DepositType depositType, String depositSource, Double amount, LocalDate depositDate, LocalDateTime createdAt, Long creatorId) {
        return new BankDepositTransactionEntity(depositType, depositSource, amount, depositDate, createdAt, creatorId);
    }

}
