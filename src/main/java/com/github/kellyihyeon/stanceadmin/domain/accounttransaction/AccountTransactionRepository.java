package com.github.kellyihyeon.stanceadmin.domain.accounttransaction;

import com.github.kellyihyeon.stanceadmin.domain.membershipfeedeposit.MembershipFeeDepositTransaction;

public interface AccountTransactionRepository {

    Long createMembershipFeeDepositTransaction(MembershipFeeDepositTransaction domain);

    void saveAccountTransaction(AccountTransaction accountTransaction);

    AccountTransaction findLatestAccountTransaction();
}
