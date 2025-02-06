package com.example.siqa.model.repository;

import com.example.siqa.model.da.Provider;
import com.example.siqa.model.da.Quote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuoteRepository extends JpaRepository<Quote, Long> {
    boolean existsByProvider_IdAndCoverageType(Long providerId, String coverageType);
}