package com.github.kellyihyeon.stanceadmin.presentation.paymentroster;

import com.github.kellyihyeon.stanceadmin.application.paymentroster.PaymentRosterService;
import com.github.kellyihyeon.stanceadmin.application.paymentroster.dto.MembershipFeePaymentRosterResponse;
import com.github.kellyihyeon.stanceadmin.application.paymentroster.dto.PaymentRosterForm;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Month;
import java.time.Year;
import java.util.List;

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

    @GetMapping("/payment-rosters/membership-fee/{year}/{month}")
    public ResponseEntity<List<MembershipFeePaymentRosterResponse>> getMembershipFeePaymentRoster(
            @PathVariable Year year,
            @PathVariable Month month
    ) {
        List<MembershipFeePaymentRosterResponse> membershipFeePaymentRosters = paymentRosterService.getMembershipFeePaymentRoster(year, month);
        return ResponseEntity.ok(membershipFeePaymentRosters);
    }
}
