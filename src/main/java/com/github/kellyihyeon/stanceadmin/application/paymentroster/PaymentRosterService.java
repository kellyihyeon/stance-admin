package com.github.kellyihyeon.stanceadmin.application.paymentroster;

import com.github.kellyihyeon.stanceadmin.application.paymentroster.dto.PaymentRosterForm;
import com.github.kellyihyeon.stanceadmin.domain.paymentroster.PaymentRoster;
import com.github.kellyihyeon.stanceadmin.infrastructure.repository.paymentroster.PaymentRosterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentRosterService {

    private final PaymentRosterRepository paymentRosterRepository;


    public void createPaymentRosters(PaymentRosterForm paymentRosterForm) {
        List<PaymentRoster> paymentRosters = new ArrayList<>();

        for (Long memberId : paymentRosterForm.memberIds()) {
            paymentRosters.add(
                    PaymentRoster.builder()
                            .category(paymentRosterForm.category())
                            .applicationDate(paymentRosterForm.applicationDate())
                            .dueDate(paymentRosterForm.dueDate())
                            .amount(paymentRosterForm.amount())
                            .memberId(memberId)
                            .creatorId(1L)
                            .createdDate(LocalDateTime.now())
                            .build());
        }

        paymentRosterRepository.saveAll(paymentRosters);
    }
}
