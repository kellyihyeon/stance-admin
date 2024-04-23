package com.github.kellyihyeon.stanceadmin.infrastructure.repository.accountbook;

import com.github.kellyihyeon.stanceadmin.domain.accountbook.AccountBook;
import com.github.kellyihyeon.stanceadmin.domain.accountbook.AccountBookRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AccountBookRepository extends JpaRepository<AccountBook, Long>, AccountBookRepositoryCustom {

    Optional<AccountBook> findTopByOrderByIdDesc();

    List<AccountBook> findByTransactionDateBetween(LocalDate start, LocalDate end);
}
