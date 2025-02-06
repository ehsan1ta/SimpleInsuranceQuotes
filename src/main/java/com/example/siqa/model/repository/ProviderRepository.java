package com.example.siqa.model.repository;

import com.example.siqa.model.da.Provider;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProviderRepository extends JpaRepository<Provider, Long> {
}