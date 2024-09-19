package com.github.kellyihyeon.stanceadmin.presentation;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/event-applicant")
    public String eventApplicant() {
        return "eventApplicant";
    }
}
