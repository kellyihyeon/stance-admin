package com.github.kellyihyeon.stanceadmin.presentation.withdraw;

import com.github.kellyihyeon.stanceadmin.application.accountbook.AccountBookService;
import com.github.kellyihyeon.stanceadmin.application.withdraw.dto.TransferRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/withdraw")
public class WithdrawController {

    private final AccountBookService accountBookService;

    @PostMapping("/transfer")
    void withdrawByTransfer(
            @RequestBody TransferRequest transferRequest
    ) {
        accountBookService.processTransferWithdrawal(transferRequest);
    }
}
