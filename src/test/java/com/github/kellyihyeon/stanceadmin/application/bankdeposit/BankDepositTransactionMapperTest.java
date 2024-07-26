package com.github.kellyihyeon.stanceadmin.application.bankdeposit;

import com.github.kellyihyeon.stanceadmin.application.bankdeposit.dto.BankDepositCreation;
import com.github.kellyihyeon.stanceadmin.domain.bankdeposit.BankDepositTransaction;
import com.github.kellyihyeon.stanceadmin.domain.bankdeposit.DepositType;
import com.github.kellyihyeon.stanceadmin.infrastructure.repository.bankdeposit.BankDepositTransactionEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = BankDepositTransactionMapperImpl.class)
class BankDepositTransactionMapperTest {

    @Autowired
    BankDepositTransactionMapper mapper;


    @Test
    void entity를_domain으로_변환() {
        Long loggedInId = 999L;
        LocalDateTime now = LocalDateTime.now();
        BankDepositTransactionEntity entity = BankDepositTransactionEntity.create(
                DepositType.CASHBACK,
                "신한은행 체크카드 캐시백",
                (double) 3000,
                LocalDate.of(2024, 7, 23),
                now,
                loggedInId
        );

        BankDepositTransaction domain = mapper.toDomain(entity);

        assertThat(domain).isInstanceOf(BankDepositTransaction.class);
    }

    @Test
    void domain을_entity로_변환() {
        Long loggedInId = 999L;
        LocalDateTime now = LocalDateTime.now();
        BankDepositTransaction domain = BankDepositTransaction.create(
                DepositType.CASHBACK,
                "신한은행 체크카드 캐시백",
                (double) 3000,
                LocalDate.of(2024, 7, 23),
                loggedInId,
                now);

        BankDepositTransactionEntity entity = mapper.toEntity(domain);

        assertThat(entity).isInstanceOf(BankDepositTransactionEntity.class);
    }

    @Test
    void serviceDto를_domain으로_변환() {
        Long loggedInId = 999L;
        LocalDateTime now = LocalDateTime.now();
        BankDepositCreation serviceDto = BankDepositCreation.create(
                DepositType.CASHBACK,
                "신한은행 체크카드 캐시백",
                (double) 3000,
                LocalDate.of(2024, 7, 23),
                "상세 내용");

        BankDepositTransaction domain = mapper.toDomain(serviceDto, loggedInId, now);

        assertThat(domain).isInstanceOf(BankDepositTransaction.class);
    }
}