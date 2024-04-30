package com.github.kellyihyeon.stanceadmin.infrastructure.repository.deposit;

import com.github.kellyihyeon.stanceadmin.domain.deposit.Deposit;
import com.github.kellyihyeon.stanceadmin.domain.deposit.DepositCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Month;
import java.time.Year;

public interface DepositRepository extends JpaRepository<Deposit, Long> {

    Long countByCategoryAndDueYearAndDueMonth(DepositCategory category, Year dueYear, Month dueMonth);
}
