package com.github.kellyihyeon.stanceadmin.domain.paymentroster;

import com.github.kellyihyeon.stanceadmin.domain.deposit.DepositCategory;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "payment_rosters")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PaymentRoster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "category", nullable = false)
    private DepositCategory category;

    @Column(name = "applicationDate", nullable = false)
    private LocalDate applicationDate;

    @Column(name = "dueDate", nullable = false)
    private LocalDate dueDate;

    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    @Column(name = "memberId", nullable = false)
    private Long memberId;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private PaymentRosterStatus status;

    @Column(name = "creator_id", nullable = false)
    private Long creatorId;

    @Column(name = "created_date", nullable = false)
    private LocalDateTime createdDate;

    @Column(name = "updater_id")
    private Long updaterId;

    @Column(name = "updated_date")
    private LocalDateTime updatedDate;


    @Builder
    public PaymentRoster(
            DepositCategory category,
            LocalDate applicationDate,
            LocalDate dueDate,
            BigDecimal amount,
            Long memberId,
            Long creatorId,
            LocalDateTime createdDate
    ) {
        this.category = category;
        this.applicationDate = applicationDate;
        this.dueDate = dueDate;
        this.amount = amount;
        this.memberId = memberId;
        this.status = PaymentRosterStatus.COMPLETED;
        this.creatorId = creatorId;
        this.createdDate = createdDate;
    }
}
