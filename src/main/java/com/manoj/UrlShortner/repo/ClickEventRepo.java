package com.manoj.UrlShortner.repo;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.manoj.UrlShortner.model.ClickEvent;
import com.manoj.UrlShortner.model.UrlMapping;

@Repository
public interface ClickEventRepo extends JpaRepository<ClickEvent, Long> {
    List<ClickEvent> findByUrlMappingAndClickDateBetween(UrlMapping mapping, LocalDateTime startDate,
            LocalDateTime endDate);

    List<ClickEvent> findByUrlMappingInAndClickDateBetween(List<UrlMapping> urlMappings, LocalDateTime startDate,
            LocalDateTime endDate);

}
