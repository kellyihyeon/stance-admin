package com.github.kellyihyeon.stanceadmin.domain.cardpayment;

import com.github.kellyihyeon.stanceadmin.domain.transfertransaction.ExpenseCategory;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

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

}
