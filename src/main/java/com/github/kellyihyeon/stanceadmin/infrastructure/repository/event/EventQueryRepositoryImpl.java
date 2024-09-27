package com.github.kellyihyeon.stanceadmin.infrastructure.repository.event;

import com.github.kellyihyeon.stanceadmin.infrastructure.querydsl.EventProjection;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EventQueryRepositoryImpl implements EventQueryRepository {

    @Override
    public List<EventProjection> getEvents(PageRequest pageable) {
        return null;
    }

}
