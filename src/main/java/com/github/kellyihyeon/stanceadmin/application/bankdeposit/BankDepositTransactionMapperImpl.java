package com.github.kellyihyeon.stanceadmin.application.bankdeposit;

import com.github.kellyihyeon.stanceadmin.application.bankdeposit.dto.BankDepositCreation;
import com.github.kellyihyeon.stanceadmin.domain.bankdeposit.BankDepositTransaction;
import com.github.kellyihyeon.stanceadmin.infrastructure.repository.bankdeposit.BankDepositTransactionEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Objects;

@Component
public class BankDepositTransactionMapperImpl implements BankDepositTransactionMapper {

    @Override
    public BankDepositTransaction toDomain(BankDepositCreation serviceDto, Long loggedInId, LocalDateTime now) {
        validateDto(serviceDto);
        return BankDepositTransaction.create(
                serviceDto.getType(),
                serviceDto.getDepositSource(),
                serviceDto.getAmount(),
                serviceDto.getDepositDate(),
                loggedInId,
                now
        );
    }

    private void validateDto(BankDepositCreation serviceDto) {
        Objects.requireNonNull(serviceDto.getType(), "type 이 null 이어선 안됩니다.");
        Objects.requireNonNull(serviceDto.getDepositSource(), "depositorSource 가 null 이어선 안됩니다.");
        Objects.requireNonNull(serviceDto.getAmount(), "amount 가 null 이어선 안됩니다.");
        Objects.requireNonNull(serviceDto.getDepositDate(), "depositDate 가 null 이어선 안됩니다.");
    }

    @Override
    public BankDepositTransactionEntity toEntity(BankDepositTransaction domain) {
        return null;
    }

    @Override
    public BankDepositTransaction toDomain(BankDepositTransactionEntity entity) {
        return null;
    }

}
