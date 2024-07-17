package com.github.kellyihyeon.stanceadmin.domain.accounttransaction;

public interface AccountTransactionRepository {

    void createMembershipFeeDepositTransaction(MembershipFeeDepositTransaction domain);
}
