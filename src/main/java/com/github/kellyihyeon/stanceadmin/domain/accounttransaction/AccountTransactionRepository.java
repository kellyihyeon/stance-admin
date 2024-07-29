package com.github.kellyihyeon.stanceadmin.domain.accounttransaction;

public interface AccountTransactionRepository {

    void saveAccountTransaction(AccountTransaction accountTransaction);

    AccountTransaction findLatestAccountTransaction();
}
