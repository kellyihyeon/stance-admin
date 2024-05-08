package com.github.kellyihyeon.stanceadmin.presentation.paymentroster;

import com.github.kellyihyeon.stanceadmin.application.paymentroster.PaymentRosterService;
import com.github.kellyihyeon.stanceadmin.application.paymentroster.dto.PaymentRosterForm;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PaymentRosterController {

    private final PaymentRosterService paymentRosterService;

    @PostMapping("/payment-roster")
    public ResponseEntity<Void> createPaymentRosters(
            @RequestBody PaymentRosterForm paymentRosterForm
    ) {
        paymentRosterService.createPaymentRosters(paymentRosterForm);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
