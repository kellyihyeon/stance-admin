package com.github.kellyihyeon.stanceadmin.infrastructure.repository.eventapplicant;

import jakarta.persistence.*;

@Entity
@Table(name = "event_applicant")
public class EventApplicantEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
