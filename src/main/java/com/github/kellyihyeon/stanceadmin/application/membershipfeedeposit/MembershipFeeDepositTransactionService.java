package com.github.kellyihyeon.stanceadmin.application.membershipfeedeposit;

import com.github.kellyihyeon.stanceadmin.application.membershipfeedeposit.dto.DepositDateCondition;
import com.github.kellyihyeon.stanceadmin.models.DepositRateResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MembershipFeeDepositTransactionService {

    public List<DepositRateResponse> getDepositRate(DepositDateCondition depositDateCondition) {
        validateDepositDate(depositDateCondition);
        return null;
    }

    private void validateDepositDate(DepositDateCondition depositDateCondition) {
        if (depositDateCondition.year() < 2023) {
            throw new IllegalArgumentException("year 를 2023년 이상으로 지정해주세요.");
        }

        if (depositDateCondition.month() < 1 || depositDateCondition.month() > 12) {
            throw new IllegalArgumentException("month 는 1에서 12 사이 이어야 해요.");
        }
    }
}
