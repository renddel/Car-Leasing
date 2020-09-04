package com.carLease.carLease.repository;

import com.carLease.carLease.model.CreditApplication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditApplicationRepository extends JpaRepository<CreditApplication, Long> {
}
