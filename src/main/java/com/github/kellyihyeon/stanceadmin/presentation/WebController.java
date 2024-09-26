package com.github.kellyihyeon.stanceadmin.presentation;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/event-fee-deposit-tracker")
    public String eventApplicant() {
        return "eventFeeDepositTracker";
    }

    @GetMapping("/budget-book-registration")
    public String budgetBookRegistration() {
        return "budgetBookRegistration";
    }

    @GetMapping("/event-applicant")
    public String manageEventApplicant() {
        return "eventApplicant";
    }

    @GetMapping("/budget-book-transaction-tracker")
    public String allBudgetBookTransactionTracker() {
        return "budgetBookTransactionTracker";
    }
}
