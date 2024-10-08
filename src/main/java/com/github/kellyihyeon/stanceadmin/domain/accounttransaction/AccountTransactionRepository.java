package com.github.kellyihyeon.stanceadmin.domain.accounttransaction;

import java.time.LocalDate;

public interface AccountTransactionRepository {

    void saveAccountTransaction(AccountTransaction accountTransaction);

    AccountTransaction findLatestAccountTransaction();

    void updateBalanceFrom(LocalDate fromTransactionDate);

}
