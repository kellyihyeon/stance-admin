package com.github.kellyihyeon.stanceadmin.domain.accounttransaction;

import java.time.LocalDate;
import java.util.List;

public interface AccountTransactionRepository {

    void saveAccountTransaction(AccountTransaction accountTransaction);

    AccountTransaction findLatestAccountTransaction();

    List<AccountTransaction> findAccountTransactionFrom(LocalDate fromTransactionDate);

    void saveAll(List<AccountTransaction> transactions);

}
