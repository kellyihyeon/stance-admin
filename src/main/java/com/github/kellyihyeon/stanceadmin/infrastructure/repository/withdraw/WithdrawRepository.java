package com.github.kellyihyeon.stanceadmin.infrastructure.repository.withdraw;

import com.github.kellyihyeon.stanceadmin.domain.withdraw.Withdraw;
import com.github.kellyihyeon.stanceadmin.domain.withdraw.WithdrawRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WithdrawRepository extends JpaRepository<Withdraw, Long>, WithdrawRepositoryCustom {
}
