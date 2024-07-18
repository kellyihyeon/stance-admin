package com.github.kellyihyeon.stanceadmin.infrastructure.repository.accounttransaction;

import com.github.kellyihyeon.stanceadmin.domain.member.MemberType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "membership_fee_deposit_transactions")
public class MemberShipFeeDepositTransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "depositor_id", nullable = false)
    private Long depositorId;

    @Column(name = "deposit_date", nullable = false)
    private LocalDate depositDate;

    @Column(name = "amount", nullable = false)
    private Double amount;

    @Column(name = "due_date", nullable = false)
    private LocalDate dueDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "member_type", nullable = false)
    private MemberType memberType;

    @Column(name = "description")
    private String description;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "creator_id", nullable = false)
    private Long creatorId;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "updatder_id")
    private Long updaterId;

    private MemberShipFeeDepositTransactionEntity(Long depositorId, LocalDate depositDate, Double amount, LocalDate dueDate, MemberType memberType, String description, LocalDateTime createdAt, Long creatorId) {
        this.depositorId = depositorId;
        this.depositDate = depositDate;
        this.amount = amount;
        this.dueDate = dueDate;
        this.memberType = memberType;
        this.description = description;
        this.createdAt = createdAt;
        this.creatorId = creatorId;
    }

    public static MemberShipFeeDepositTransactionEntity create(Long depositorId, LocalDate depositDate, Double amount, LocalDate dueDate, MemberType memberType, String description, LocalDateTime createdAt, Long creatorId) {
        return new MemberShipFeeDepositTransactionEntity(
                depositorId,
                depositDate,
                amount,
                dueDate,
                memberType,
                description,
                createdAt,
                creatorId
        );
    }
}
