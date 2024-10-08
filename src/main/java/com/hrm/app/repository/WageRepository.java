package com.hrm.app.repository;

import com.hrm.app.domain.Wage;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Wage entity.
 */
@SuppressWarnings("unused")
@Repository
public interface WageRepository extends JpaRepository<Wage, Long> {}
