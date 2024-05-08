package com.github.kellyihyeon.stanceadmin.infrastructure.repository.paymentroster;

import com.github.kellyihyeon.stanceadmin.domain.paymentroster.PaymentRoster;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRosterRepository extends JpaRepository<PaymentRoster, Long> {
}
