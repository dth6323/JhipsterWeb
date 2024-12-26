package com.hrm.app.repository;

import com.hrm.app.domain.TotalAttendSalary;
import com.hrm.app.service.dto.EmployeeAttendDTO;
import java.util.List;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the TotalAttendSalary entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TotalAttendSalaryRepository extends JpaRepository<TotalAttendSalary, Long> {
    @Query("SELECT e.id, COUNT(a.id) FROM Employee e JOIN e.attendances a GROUP BY e.id")
    List<Object[]> findEmployeeAttendance();
}
