package com.github.kellyihyeon.stanceadmin.application.accounttransaction;

import com.github.kellyihyeon.stanceadmin.infrastructure.querydsl.AccountTransactionProjection;
import com.github.kellyihyeon.stanceadmin.infrastructure.repository.accounttransaction.AccountTransactionQueryRepository;
import com.github.kellyihyeon.stanceadmin.models.AccountTransactionResponse;
import com.github.kellyihyeon.stanceadmin.models.PagedAccountTransactionResponse;
import com.github.kellyihyeon.stanceadmin.models.PagingMetadata;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountTransactionQueryService {

    private final AccountTransactionQueryRepository queryRepository;

    public PagedAccountTransactionResponse getAllAccountTransactions(PageRequest pageable) {
        List<AccountTransactionProjection> projections = queryRepository.findAccountTransactionWithJoins(pageable);

        List<AccountTransactionResponse> allAccountTransactions = projections.stream()
                .map(projection -> {
                    AccountTransactionResponse response = new AccountTransactionResponse();
                    response.id(projection.getId());
                    response.transactionId(projection.getTransactionId());
                    response.transactionType(projection.getTransactionType().name());
                    response.detailType(projection.getDetailType());
                    response.transactionParty(projection.getTransactionParty());
                    response.amount(projection.getAmount());
                    response.balance(projection.getBalance());

                    return response;
                })
                .collect(Collectors.toList());


        PageImpl<AccountTransactionResponse> pagedTransactions = new PageImpl<>(allAccountTransactions, pageable, allAccountTransactions.size());
        PagingMetadata pagingMetadata = new PagingMetadata(
                pagedTransactions.getNumber(),
                pagedTransactions.getSize(),
                pagedTransactions.getTotalElements(),
                pagedTransactions.getTotalPages()
        );

        return new PagedAccountTransactionResponse(allAccountTransactions, pagingMetadata);
    }
}
