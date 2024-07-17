package com.github.kellyihyeon.stanceadmin.infrastructure.repository.account;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaAccountEntityRepository extends JpaRepository<AccountEntity, Long> {

    Optional<AccountEntity> findByIsDefault(Boolean isDefault);
}
