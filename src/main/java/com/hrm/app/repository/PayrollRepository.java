package com.hrm.app.repository;

import com.hrm.app.domain.Payroll;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Payroll entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PayrollRepository extends JpaRepository<Payroll, Long> {}