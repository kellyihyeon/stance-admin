package com.github.kellyihyeon.stanceadmin.presentation.account;

import com.github.kellyihyeon.stanceadmin.apis.AccountApi;
import com.github.kellyihyeon.stanceadmin.models.Account;
import com.github.kellyihyeon.stanceadmin.models.AccountBalance;
import com.github.kellyihyeon.stanceadmin.models.Accounts;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
public class AccountController implements AccountApi {


    @Override
    public ResponseEntity<Void> createAccount(Account account) {
        return null;
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
