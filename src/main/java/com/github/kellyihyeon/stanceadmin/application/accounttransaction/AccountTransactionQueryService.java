package com.github.kellyihyeon.stanceadmin.application.accounttransaction;

import com.github.kellyihyeon.stanceadmin.infrastructure.querydsl.AccountTransactionProjection;
import com.github.kellyihyeon.stanceadmin.infrastructure.repository.accounttransaction.AccountTransactionQueryRepository;
import com.github.kellyihyeon.stanceadmin.models.PagedAccountTransactionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountTransactionQueryService {

    private final AccountTransactionQueryRepository queryRepository;

    public PagedAccountTransactionResponse getAllAccountTransactions(PageRequest pageable) {
        List<AccountTransactionProjection> projections = queryRepository.findAccountTransactionWithJoins(pageable);

        // convert projections to PagedAccountTransactionResponse

        return new PagedAccountTransactionResponse();

    }
}
