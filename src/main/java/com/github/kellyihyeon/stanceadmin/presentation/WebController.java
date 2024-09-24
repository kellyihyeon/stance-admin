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
}
