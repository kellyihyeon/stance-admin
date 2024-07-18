package com.github.kellyihyeon.stanceadmin.domain.accounttransaction;

public interface AccountTransactionRepository {

    Long createMembershipFeeDepositTransaction(MembershipFeeDepositTransaction domain);

    void createAccountTransaction(AccountTransaction accountTransaction);
}
