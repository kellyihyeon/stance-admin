package com.github.kellyihyeon.stanceadmin.domain.eventdeposit;

import com.github.kellyihyeon.stanceadmin.domain.eventapplicantregistry.EventApplicantDepositRegistry;

import java.util.List;

public interface EventFeeDepositTransactionRepository {

    Long saveEventDepositTransaction(EventDepositTransaction domain);

    List<EventApplicantDepositRegistry> getEventApplicantRegistriesByEventId(Long eventId);

}
