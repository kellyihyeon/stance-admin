package com.github.kellyihyeon.stanceadmin.presentation.account;

import com.github.kellyihyeon.stanceadmin.apis.AccountApi;
import com.github.kellyihyeon.stanceadmin.application.account.AccountService;
import com.github.kellyihyeon.stanceadmin.application.account.dto.AccountCreation;
import com.github.kellyihyeon.stanceadmin.domain.account.AccountStatus;
import com.github.kellyihyeon.stanceadmin.domain.account.Bank;
import com.github.kellyihyeon.stanceadmin.models.AccountBalance;
import com.github.kellyihyeon.stanceadmin.models.AccountInput;
import com.github.kellyihyeon.stanceadmin.models.Accounts;
import com.github.kellyihyeon.stanceadmin.presentation.TimeConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class AccountController implements AccountApi {

    private final AccountService accountService;

    @Override
    public ResponseEntity<Void> createAccount(AccountInput accountInput) {
        AccountCreation accountCreation = new AccountCreation(
                accountInput.getIsDefault(),
                accountInput.getAccountNickname(),
                accountInput.getAccountHolder(),
                accountInput.getAccountNumber(),
                Bank.valueOf(accountInput.getBank().getValue()),
                TimeConverter.convertToLocalDate(accountInput.getUsageStartDate()),
                TimeConverter.convertToLocalDate(accountInput.getUsageEndDate()),
                AccountStatus.valueOf(accountInput.getAccountStatus().getValue()));

        accountService.createAccount(accountCreation);
        return ResponseEntity.created(URI.create("")).build();
    }

    @Override
    public ResponseEntity<Void> deleteAccountById(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<List<Accounts>> getAllAccounts() {
        return null;
    }

    @Override
    public ResponseEntity<AccountBalance> getBalance() {
        AccountBalance balance = new AccountBalance().balance(new BigDecimal("870000"));
        return ResponseEntity.ok(balance);
    }

    @Override
    public ResponseEntity<Void> updateAccountStatus(Long id, String status) {
        return null;
    }
}
