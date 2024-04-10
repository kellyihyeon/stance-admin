package com.github.kellyihyeon.stanceadmin.infrastructure.repository.accountbook;

import com.github.kellyihyeon.stanceadmin.domain.accountbook.AccountBook;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountBookRepository extends JpaRepository<AccountBook, Long> {

    Optional<AccountBook> findTopByOrderByIdDesc();
}
