package com.github.kellyihyeon.stanceadmin.infrastructure.entity.trasfertransaction;

import jakarta.persistence.*;

@Entity
@Table(name = "transfer_transactions")
public class TransferTransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
