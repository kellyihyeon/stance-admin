package com.github.kellyihyeon.stanceadmin.infrastructure.repository.deposit;

import com.github.kellyihyeon.stanceadmin.domain.deposit.Deposit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepositRepository extends JpaRepository<Deposit, Long> {
}
