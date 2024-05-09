package com.github.kellyihyeon.stanceadmin.domain.paymentroster;

import com.github.kellyihyeon.stanceadmin.application.deposit.dto.Depositor;
import com.github.kellyihyeon.stanceadmin.domain.deposit.DepositCategory;

import java.time.Month;
import java.time.Year;
import java.util.List;

public interface PaymentRosterRepositoryCustom {


    List<Depositor> findByCategoryAndDueYearAndDueMonth(
            DepositCategory category,
            Year year,
            Month month
    );
}
