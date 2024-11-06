package com.hrm.app.repository;

import com.hrm.app.domain.Payroll;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Payroll entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PayrollRepository extends JpaRepository<Payroll, Long> {
    @Query(
        """
            SELECT e.id, e.name,w.baseSalary, COUNT(a.id) AS attendanceDays
                FROM Attendance a
                JOIN Employee e ON a.employee.id = e.id
                JOIN Payroll p ON e.id = p.employee.id
                JOIN Wage w ON p.wage.id = w.id
                WHERE a.dateOfwork BETWEEN :startDate AND :endDate
                GROUP BY e.id, e.name,w.baseSalary
        """
    )
    List<Object[]> getEmployeeSalaryAndAttendance(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    @Query(
        """
            SELECT e.id, e.name,w.baseSalary, COUNT(a.id) AS attendanceDays
                FROM Attendance a
                JOIN Employee e ON a.employee.id = e.id
                JOIN Payroll p ON e.id = p.employee.id
                JOIN Wage w ON p.wage.id = w.id
                GROUP BY e.id, e.name,w.baseSalary
        """
    )
    List<Object[]> getall();
}
